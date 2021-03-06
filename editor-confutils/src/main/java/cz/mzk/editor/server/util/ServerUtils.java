/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.mzk.editor.server.util;

import java.io.IOException;

import java.lang.reflect.Field;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.UnknownHostException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import javax.xml.bind.JAXBElement;

import javax.inject.Inject;

import com.google.inject.Provider;
import com.gwtplatform.dispatch.shared.ActionException;

import org.apache.log4j.Logger;

import org.springframework.security.core.context.SecurityContext;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.EDITOR_RIGHTS;
import cz.mzk.editor.server.EditorUserAuthentication;
import cz.mzk.editor.server.URLS;
import cz.mzk.editor.server.DAO.DAOUtils;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.config.EditorConfiguration;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerUtils.
 */
public class ServerUtils {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ServerUtils.class);

    /** The config. */
    @Inject
    private static EditorConfiguration config;

    /** The dao utils. */
    @Inject
    private static DAOUtils daoUtils;

    /** The http session provider. */
    @Inject
    private static Provider<HttpSession> httpSessionProvider;

    /**
     * Checks if is caused by exception.
     * 
     * @param t
     *        the t
     * @param type
     *        the type
     * @return true, if is caused by exception
     */
    public static boolean isCausedByException(Throwable t, Class<? extends Exception> type) {
        if (t == null) return false;
        Throwable aux = t;
        while (aux != null) {
            if (type.isInstance(aux)) return true;
            aux = aux.getCause();
        }
        return false;
    }

    private static EditorUserAuthentication getEditorUserAuthentication(HttpSession session) {
        SecurityContext secContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        EditorUserAuthentication authentication = null;
        if (secContext != null) authentication = (EditorUserAuthentication) secContext.getAuthentication();
        return authentication;
    }

    public static EditorUserAuthentication getEditorUserAuthentication() {
        return getEditorUserAuthentication(httpSessionProvider.get());
    }

    private static void checkExpiredSession(HttpSession session) throws ActionException {
        EditorUserAuthentication authentication = getEditorUserAuthentication(session);
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ActionException(Constants.SESSION_EXPIRED_FLAG + URLS.ROOT()
                    + (URLS.LOCALHOST() ? URLS.LOGIN_LOCAL_PAGE : URLS.LOGIN_PAGE));
        }
    }

    public static boolean checkUserRight(Long userId, EDITOR_RIGHTS right) {
        try {
            return daoUtils.hasUserRight(userId, right);
        } catch (DatabaseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (clazz == null || clazz.getName().contains("java.util.List")
                || clazz.getName().contains("java.lang.String")) {
            return Collections.<Field> emptyList();
        }
        List<Field> fields = new ArrayList<Field>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(getAllFields(clazz.getSuperclass()));
        }
        return fields;
    }

    private enum KRAMERIUS_ACTION {
        REINDEX("reindex");

        private final String actionName;

        private KRAMERIUS_ACTION(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public static boolean reindex(String pid) {
        if (!pid.startsWith(Constants.FEDORA_UUID_PREFIX)) {
            pid = Constants.FEDORA_UUID_PREFIX + pid;
        }

        return krameriusRest(KRAMERIUS_ACTION.REINDEX, pid);
    }

    /**
     * Reindex.
     * 
     * @param pid
     *        the pid
     */
    //TODO-MR: Rest api via lrservlet must be authenticated since version 4.6 of Kramerius. Look at new Remote API!
    private static boolean krameriusRest(KRAMERIUS_ACTION action, String pid) {
        String host = config.getKrameriusHost();
        String login = config.getKrameriusLogin();
        String password = config.getKrameriusPassword();
        if (host == null || login == null || password == null) {
            return false;
        }
        String url;
        switch (action) {
            case REINDEX:
                url =
                        host + "/lr?action=start&def=reindex&out=text&params=fromKrameriusModel," + pid + ","
                                + pid + "&userName=" + login + "&pswd=" + password;
                break;
            default:
                throw new IllegalArgumentException("Undefined rest action");
        }

        if (KRAMERIUS_ACTION.REINDEX.equals(action)) {
            LOGGER.info(action.getActionName() + pid + " sending HTTP GET to " + host
                    + "/lr?action=start&def=reindex&out=text&params=fromKrameriusModel," + pid + "," + pid
                    + "&userName=***&pswd=***");
        }
        try {
            RESTHelper.get(url, login, password, false);
        } catch (MalformedURLException e) {
            LOGGER.error("Unable to " + action.getActionName(), e);
            return false;
        } catch (IOException e) {
            LOGGER.error("Unable to " + action.getActionName(), e);
            return false;
        }
        return true;
    }

    private static boolean hasOnlyNullFields(Object object) {
        if (object == null) {
            return true;
        }
        boolean isNull = true;
        try {
            if (object.getClass().getName().contains("JAXBElement")) {
                JAXBElement<?> elem = (JAXBElement<?>) object;
                if (!elem.isNil()) {
                    return hasOnlyNullFields(elem.getValue());
                } else
                    return false;
            } else if (object.getClass().getName().contains("java.lang.String")) {
                return "".equals(((String) object).trim());
            }
            List<Field> fields = getAllFields(object.getClass());

            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.get(object) != null) {
                    if (field.getType().getName().contains("java.util.List")
                            && !((List<?>) field.get(object)).isEmpty()) {
                        @SuppressWarnings("unchecked")
                        List<Object> list = (List<Object>) field.get(object);
                        for (int i = 0; i < list.size(); i++) {
                            if (!hasOnlyNullFields(list.get(i))) {
                                isNull = false;
                                break;
                            }
                        }
                        if (!isNull) {
                            break;
                        }
                    } else if (field.getType().getName().contains("java.lang.String")
                            && !"".equals(((String) field.get(object)).trim())) {
                        isNull = false;
                        break;
                    } else if (field.getType().getName().contains("CodeOrText")
                            || field.getType().getName().contains("NameTypeAttribute")
                            || field.getType().getName().contains("PlaceAuthority")
                            || field.getType().getName().contains("Yes")) {
                        Field auxField = field.getType().getDeclaredField("value");
                        auxField.setAccessible(true);
                        String s = (String) auxField.get(field.get(object));
                        if (!"".equals(s)) {
                            isNull = false;
                            break;
                        }
                    } else if (field.getType().getName().contains("long")) {
                        continue;
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (SecurityException e) {

        } catch (NoSuchFieldException e) {

        }
        return isNull;
    }

    public static <T> T collapseStructure(T object) {
        if (hasOnlyNullFields(object)) {
            return null;
        }
        List<Field> fields = getAllFields(object.getClass());
        try {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.get(object) != null) {
                    if (field.getType().getName().contains("java.util.List")) {
                        @SuppressWarnings("unchecked")
                        List<Object> list = (List<Object>) field.get(object);
                        List<Object> newList = new ArrayList<Object>(list.size());
                        boolean isNull = true;
                        for (int i = 0; i < list.size(); i++) {
                            Object o = collapseStructure(list.get(i));
                            if (o != null) {
                                isNull = false;
                                newList.add(o);
                            }
                        }
                        field.set(object, isNull ? null : newList);
                        continue;
                    } else if (field.getType().getName().contains("java.lang.String")) {
                        if ("".equals(((String) field.get(object)).trim())) {
                            field.set(object, null);
                        }
                        continue;
                    } else if (field.getType().getName().contains("CodeOrText")
                            || field.getType().getName().contains("NameTypeAttribute")
                            || field.getType().getName().contains("PlaceAuthority")
                            || field.getType().getName().contains("Yes")) {
                        Field auxField = field.getType().getDeclaredField("value");
                        auxField.setAccessible(true);
                        String s = (String) auxField.get(field.get(object));
                        if ("".equals(s)) {
                            field.set(object, null);
                        }
                        continue;
                    } else {
                        collapseStructure(field.get(object));
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (SecurityException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        } catch (NoSuchFieldException e) {
            LOGGER.error("Unable to inspect the structure via reflection", e);
        }
        if (hasOnlyNullFields(object)) {
            return null;
        }
        return object;
    }

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
    }

    public static boolean is64bit() {
        String os = System.getProperty("os.arch").toLowerCase();
        return (os.indexOf("64") >= 0);
    }

    public static String getPlatform() {
        if (ServerUtils.isUnix()) {
            if (ServerUtils.is64bit()) {
                return "Linux-x86-64";
            } else {
                return "Linux-x86-32";
            }
        } else if (ServerUtils.isWindows()) {
            return "Win32";
        } else if (ServerUtils.isMac()) {
            return "Mac-x86";
        } else
            return "Solaris-x86";
    }

    public static String getHostname() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOGGER.error("Unable to obtain the hostname", e);
            return null;
        }
        return addr.getHostName();
    }

    public static boolean checkAvailability(String url, String usr, String pass) {
        boolean status = true;
        try {
            URLConnection con = RESTHelper.openConnection(url, usr, pass, false);
            if (con instanceof HttpURLConnection) {
                HttpURLConnection httpConnection = (HttpURLConnection) con;
                int resp = httpConnection.getResponseCode();
                if (resp < 200 || resp >= 308) {
                    status = false;
                    LOGGER.info("Server " + url + " answered with HTTP code "
                            + httpConnection.getResponseCode());
                }
            } else {
                status = false;
            }
        } catch (MalformedURLException e) {
            status = false;
            e.printStackTrace();
        } catch (IOException e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }

}
