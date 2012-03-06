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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


// TODO: Auto-generated Javadoc
/**
 * <p>Java class for physicalDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="physicalDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="form" type="{http://www.loc.gov/mods/v3}stringPlusAuthorityPlusType"/>
 *         &lt;element name="reformattingQuality">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="access"/>
 *               &lt;enumeration value="preservation"/>
 *               &lt;enumeration value="replacement"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="internetMediaType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="digitalOrigin">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="born digital"/>
 *               &lt;enumeration value="reformatted digital"/>
 *               &lt;enumeration value="digitized microfilm"/>
 *               &lt;enumeration value="digitized other analog"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="note" type="{http://www.loc.gov/mods/v3}noteType"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.loc.gov/mods/v3}language"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "physicalDescriptionType", propOrder = {
    "formOrReformattingQualityOrInternetMediaType"
})
public class PhysicalDescriptionType {

    /** The form or reformatting quality or internet media type. */
    @XmlElementRefs({
        @XmlElementRef(name = "internetMediaType", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "digitalOrigin", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "extent", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "reformattingQuality", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "note", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "form", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> formOrReformattingQualityOrInternetMediaType;
    
    /** The xml lang. */
    @XmlAttribute(name = "lang")
    protected String xmlLang;
    
    /** The lang. */
    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    
    /** The script. */
    @XmlAttribute
    protected String script;
    
    /** The transliteration. */
    @XmlAttribute
    protected String transliteration;

    /**
     * Gets the value of the formOrReformattingQualityOrInternetMediaType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formOrReformattingQualityOrInternetMediaType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     * getFormOrReformattingQualityOrInternetMediaType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     *
     * @return the form or reformatting quality or internet media type
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link NoteType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link StringPlusAuthorityPlusType }{@code >}
     */
    public List<JAXBElement<?>> getFormOrReformattingQualityOrInternetMediaType() {
        if (formOrReformattingQualityOrInternetMediaType == null) {
            formOrReformattingQualityOrInternetMediaType = new ArrayList<JAXBElement<?>>();
        }
        return this.formOrReformattingQualityOrInternetMediaType;
    }

    /**
     * Gets the value of the xmlLang property.
     *
     * @return the xml lang
     * possible object is
     * {@link String }
     */
    public String getXmlLang() {
        return xmlLang;
    }

    /**
     * Sets the value of the xmlLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlLang(String value) {
        this.xmlLang = value;
    }

    /**
     * Gets the value of the lang property.
     *
     * @return the lang
     * possible object is
     * {@link String }
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the script property.
     *
     * @return the script
     * possible object is
     * {@link String }
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScript(String value) {
        this.script = value;
    }

    /**
     * Gets the value of the transliteration property.
     *
     * @return the transliteration
     * possible object is
     * {@link String }
     */
    public String getTransliteration() {
        return transliteration;
    }

    /**
     * Sets the value of the transliteration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransliteration(String value) {
        this.transliteration = value;
    }

}
