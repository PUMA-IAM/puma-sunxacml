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

/**
 * Interface used for declaring attribute counters as used in BasicEvaluationCtx.
 * 
 * @author Maarten Decat
 *
 */
public interface AttributeCounter {
	
	/**
	 * Indicates that an attribute has been fetched from the cache.
	 */
	public void countCacheFetch();
	
	/**
	 * Indicates that an attribute has been fetched from outside the cache.
	 */
	public void countNoncacheFetch();

}
