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

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionException.
 */
public class ConnectionException
        extends IOException {

    /**
     * 
     */
    private static final long serialVersionUID = 5932923648539397259L;

    /**
     * Instantiates a new connection exception.
     * 
     * @param msg
     *        the msg
     */
    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
