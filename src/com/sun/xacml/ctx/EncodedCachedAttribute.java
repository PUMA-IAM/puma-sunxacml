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

package com.sun.xacml.ctx;

import java.util.List;

import com.sun.xacml.SimpleAttributeValue;
import com.sun.xacml.attr.BagAttribute;

/**
 * Class used for representing a cached evaluation result.
 * 
 * @author Maarten Decat
 * 
 */
public class EncodedCachedAttribute {
	
	private String type;
	
	private String id;
	
	private List<SimpleAttributeValue> encodedBagAttribute;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<SimpleAttributeValue> getEncodedBagAttribute() {
		return encodedBagAttribute;
	}

	public void setEncodedBagAttribute(
			List<SimpleAttributeValue> encodedBagAttribute) {
		this.encodedBagAttribute = encodedBagAttribute;
	}

	/**
	 * Default constructor.
	 */
	public EncodedCachedAttribute() {
		
	}

	/**
	 * 
	 * @param type
	 * @param id
	 * @param value
	 */
	public EncodedCachedAttribute(String type, String id, List<SimpleAttributeValue> encodedBagAttribute) {
		this.type = type;
		this.id = id;
		this.encodedBagAttribute = encodedBagAttribute;
	}
	
	/**
	 * 
	 */
	public EncodedCachedAttribute(CachedAttribute attr) {
		this(attr.getType(), attr.getId(), attr.getValue().encodeToSet());
	}
	
	/**
	 * Convert to CachedAttribute.
	 */
	public CachedAttribute toCachedAttribute() {
		return new CachedAttribute(type, id, BagAttribute.fromEncodedSet(encodedBagAttribute));
	}
	
}
