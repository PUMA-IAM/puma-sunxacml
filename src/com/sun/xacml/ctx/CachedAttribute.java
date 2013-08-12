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

import java.net.URI;

import com.sun.xacml.attr.BagAttribute;

/**
 * Class used for representing a cached evaluation result.
 * 
 * @author Maarten Decat
 * 
 */
public class CachedAttribute {
	
	private String type;
	
	private String id;
	
	private BagAttribute value;
	
	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public BagAttribute getValue() {
		return value;
	}

	public CachedAttribute(URI type, URI id, BagAttribute value) {
		this(type.toString(), id.toString(), value);
	}

	public CachedAttribute(String type, String id, BagAttribute value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}
	
	public boolean matches(URI type, URI id) {
		return this.matches(type.toString(), id.toString());
	}
	
	public boolean matches(String type, String id) {
		return type.equals(this.type) && id.equals(this.id);
	}

	private boolean URIsMatch(URI first, URI second) {
		if(first == null || second == null) {
			return first == second;
		} else {
			// simplified equals() than URI.equals()
			return first.toString().equals(second.toString()); 
		}
	}
}
