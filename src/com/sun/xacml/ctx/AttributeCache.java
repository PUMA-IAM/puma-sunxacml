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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.xacml.attr.BagAttribute;

/**
 * Class used for representing an attribute cache.
 * 
 * @author Maarten Decat
 * 
 */
public class AttributeCache {
	
	private Set<CachedAttribute> cachedAttributes = new HashSet<CachedAttribute>();

	public Set<CachedAttribute> getCachedAttributes() {
		return cachedAttributes;
	}
	
	public List<EncodedCachedAttribute> getEncodedCachedAttributes() {
		List<EncodedCachedAttribute> result = new ArrayList<EncodedCachedAttribute>();
		for(CachedAttribute ca: this.getCachedAttributes()) {
			result.add(new EncodedCachedAttribute(ca));
		}
		return result;
	}

	/**
	 * Caches the given evaluation result (note that this actually caches more
	 * than only the attribute value!).
	 * 
	 * @param type
	 * @param id
	 * @param issuer
	 * @param map
	 * @param category
	 * @param designatorType
	 * @param result
	 */
	public void cacheAttribute(URI type, URI id, BagAttribute value) {
		this.cachedAttributes.add(new CachedAttribute(type, id, value));
	}

	/**
	 * Caches the given evaluation result (note that this actually caches more
	 * than only the attribute value!).
	 * 
	 * @param type
	 * @param id
	 * @param issuer
	 * @param map
	 * @param category
	 * @param designatorType
	 * @param result
	 */
	public void cacheAttribute(String type, String id, BagAttribute value) {
		this.cachedAttributes.add(new CachedAttribute(type, id, value));
	}

	/**
	 * Returns the cached result for given parameters. Returns null if no matching
	 * result is cached.
	 * 
	 * @param type
	 * @param id
	 * @param issuer
	 * @param category
	 * @param designatorType
	 * @return
	 */
	public BagAttribute getAttribute(URI type, URI id) {
		for(CachedAttribute result: this.cachedAttributes) {
			if(result.matches(type, id)) {
				return result.getValue();
			}
		}
		return null;
	}
}
