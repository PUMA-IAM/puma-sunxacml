/*******************************************************************************
 * Copyright 2013 KU Leuven Research and Developement - IBBT - Distrinet 
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Administrative Contact: dnet-project-office@cs.kuleuven.be
 *    Technical Contact: maarten.decat@cs.kuleuven.be
 *    Author: maarten.decat@cs.kuleuven.be
 ******************************************************************************/

package com.sun.xacml;

import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BooleanAttribute;
import com.sun.xacml.attr.DateAttribute;
import com.sun.xacml.attr.DateTimeAttribute;
import com.sun.xacml.attr.IntegerAttribute;
import com.sun.xacml.attr.StringAttribute;

/**
 * 
 * @author Maarten Decat
 *
 */
public class SimpleAttributeValue {
	
	private String type;
	
	private String value;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public AttributeValue getValueAsObject() {
		try {
			if(this.getType().equals(StringAttribute.identifierURI.toString())) {
				return StringAttribute.getInstance(getValue());
			} else if(this.getType().equals(IntegerAttribute.identifierURI.toString())) {
				return IntegerAttribute.getInstance(getValue());
			} else if(this.getType().equals(BooleanAttribute.identifierURI.toString())) {
				return BooleanAttribute.getInstance(getValue());
			} else if(this.getType().equals(DateAttribute.identifierURI.toString())) {
				return DateAttribute.getInstance(getValue());
			} else if(this.getType().equals(DateTimeAttribute.identifierURI.toString())) {
				return DateTimeAttribute.getInstance(getValue());
			} else {
				throw new RuntimeException("Unsupported attribute type: " + this.getType());
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error when converting attribute value: " + this.getType());
		}
	}
	
	/**
	 * Default constructor
	 */
	public SimpleAttributeValue() {
		
	}

	/**
	 * Constructor 
	 * 
	 * @param type
	 * @param id
	 * @param designatorType
	 * @param value
	 */
	public SimpleAttributeValue(String type, String value) {
		this.type = type;
		this.value = value;
	}
	
	/**
	 * Constructor 
	 * 
	 * @param type
	 * @param id
	 * @param designatorType
	 * @param value
	 */
	public SimpleAttributeValue(String type, AttributeValue value) {
		this.type = type;
		this.value = value.encode();
	}

}
