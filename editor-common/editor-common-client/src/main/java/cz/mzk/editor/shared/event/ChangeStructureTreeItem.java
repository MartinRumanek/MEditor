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

package cz.mzk.editor.shared.event;

import java.util.Map;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

import cz.mzk.editor.client.util.Constants.STRUCTURE_TREE_ITEM_ACTION;

/**
 * @author Matous Jobanek
 * @version Apr 19, 2012
 */
@GenEvent
@SuppressWarnings("unused")
public class ChangeStructureTreeItem {

    @Order(1)
    private STRUCTURE_TREE_ITEM_ACTION action;

    @Order(2)
    private Map<String, Integer> recordIdAndItsNewValue;

}
