/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
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

package cz.mzk.editor.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Matous Jobanek
 * @version Nov 30, 2012
 */
public class SecurityUtils {

    public static void redirectToRegisterPage(HttpServletRequest request, HttpServletResponse response) {
	String root = getRootURL(request);
        URLS.redirect(response, URLS.LOCALHOST() ? root.substring(0, root.indexOf("?")) + URLS.INFO_PAGE
                + root.substring(root.indexOf("?")) : root + URLS.INFO_PAGE);
    }

    public static void redirectToErrorLoginPage(HttpServletRequest request, HttpServletResponse response) {
	String root = getRootURL(request);
        URLS.redirect(response, URLS.LOCALHOST() ? root.substring(0, root.indexOf("?"))
                + URLS.LOGIN_LOCAL_PAGE + root.substring(root.indexOf("?")) + "&" + URLS.ERROR_LOGIN_SUFFIX
                : root + URLS.LOGIN_PAGE + "?" + URLS.ERROR_LOGIN_SUFFIX);
    }
    
    public static void redirectToErrorDBLoginPage(HttpServletRequest request, HttpServletResponse response) {
        String root = getRootURL(request);
        URLS.redirect(response, URLS.LOCALHOST() ? root.substring(0, root.indexOf("?"))
                + URLS.LOGIN_LOCAL_PAGE + root.substring(root.indexOf("?")) + "&" + URLS.ERROR_DB_SUFFIX
                : root + URLS.LOGIN_PAGE + "?" + URLS.ERROR_DB_SUFFIX);
    }
    
    private static String getRootURL(HttpServletRequest request) {
	return (URLS.LOCALHOST() ? "http://" : "https://")
                + request.getServerName()
                + (URLS.LOCALHOST() ? (request.getServerPort() == 80
                        || request.getServerPort() == 443 ? "" : (":" + request.getServerPort()))
                        : "") + URLS.ROOT() + (URLS.LOCALHOST() ? "?gwt.codesvr=127.0.0.1:9997" : "");
    }
}