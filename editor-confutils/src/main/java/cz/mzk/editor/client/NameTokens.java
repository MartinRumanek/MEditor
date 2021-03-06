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

package cz.mzk.editor.client;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

// TODO: Auto-generated Javadoc
/**
 * The central location of all name tokens for the application. All
 * {@link ProxyPlace} classes get their tokens from here. This class also makes
 * it easy to use name tokens as a resource within UIBinder xml files.
 * <p />
 * The public static final String is used within the annotation
 * {@link NameToken}, which can't use a method and the method associated with
 * this field is used within UiBinder which can't access static fields.
 * <p />
 * Also note the exclamation mark in front of the tokens, this is used for
 * search engine crawling support.
 * 
 * @author Christian Goudreau
 * @author Philippe Beaudoin
 */
public class NameTokens {

    /** The Constant HOME. */
    public static final String ADMIN_HOME = "adminHome";

    /** The Constant MEDIT_HOME. */
    public static final String MEDIT_HOME = "meditHome";

    /** The Constant MENU. */
    public static final String MENU = "menu";

    /** The Constant MODIFY. */
    public static final String MODIFY = "modify";

    /** The Constant MODIFY. */
    public static final String CREATE = "create";

    public static final String FIND_METADATA = "findMetadata";

    public static final String ADJUST_PAGES = "adjust";

    @SuppressWarnings("serial")
    public static List<String> getAllNameTokens() {
        return new ArrayList<String>() {

            {
                add(ADMIN_HOME);
                add(MEDIT_HOME);
                add(MENU);
                add(MODIFY);
                add(CREATE);
                add(FIND_METADATA);
                add(ADJUST_PAGES);

                add(ADMIN_MENU_BUTTONS.MY_ACOUNT);
                add(ADMIN_MENU_BUTTONS.HISTORY);
                add(ADMIN_MENU_BUTTONS.STORED_AND_LOCKS);
                add(ADMIN_MENU_BUTTONS.STATISTICS);
                add(ADMIN_MENU_BUTTONS.USERS);
            }
        };
    }

    public static final class ADMIN_MENU_BUTTONS {

        public static final String MY_ACOUNT = "myAcount";
        public static final String HISTORY = "history";
        public static final String STORED_AND_LOCKS = "stored";
        public static final String STATISTICS = "statistic";
        public static final String USERS = "users";

    }
}