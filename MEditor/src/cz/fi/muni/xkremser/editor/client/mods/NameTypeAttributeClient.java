//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.13 at 05:02:55 odp. CET 
//

package cz.fi.muni.xkremser.editor.client.mods;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum NameTypeAttributeClient implements IsSerializable {

	/**
	 * 100, 700
	 * 
	 */
	PERSONAL("personal"),

	/**
	 * 110, 710.
	 * 
	 */
	CORPORATE("corporate"),

	/**
	 * 111, 711
	 * 
	 */
	CONFERENCE("conference");
	private final String value;

	NameTypeAttributeClient(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static NameTypeAttributeClient fromValue(String v) {
		for (NameTypeAttributeClient c : NameTypeAttributeClient.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		return null;
	}

}