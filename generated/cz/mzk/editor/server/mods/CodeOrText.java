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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.14 at 07:28:52 PM CET 
//


package cz.mzk.editor.server.mods;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


// TODO: Auto-generated Javadoc
/**
 * <p>Java class for codeOrText.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="codeOrText">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="code"/>
 *     &lt;enumeration value="text"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "codeOrText")
@XmlEnum
public enum CodeOrText {

    /** The CODE. */
    @XmlEnumValue("code")
    CODE("code"),
    
    /** The TEXT. */
    @XmlEnumValue("text")
    TEXT("text");
    
    /** The value. */
    private final String value;

    /**
     * Instantiates a new code or text.
     *
     * @param v the v
     */
    CodeOrText(String v) {
        value = v;
    }

    /**
     * Value.
     *
     * @return the string
     */
    public String value() {
        return value;
    }

    /**
     * From value.
     *
     * @param v the v
     * @return the code or text
     */
    public static CodeOrText fromValue(String v) {
        for (CodeOrText c: CodeOrText.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
