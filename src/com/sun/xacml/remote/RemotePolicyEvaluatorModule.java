/*
 *  Copyright 2012 KU Leuven Research and Developement - IBBT - Distrinet 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *  Administrative Contact: dnet-project-office@cs.kuleuven.be
 *  Technical Contact: maarten.decat@cs.kuleuven.be
 *  Author: maarten.decat@cs.kuleuven.be
 */

package com.sun.xacml.remote;

import java.net.URI;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ctx.Result;

/**
 * Class used for representing a remote policy evaluator module.
 * Each module is capable of evaluating certain remote policies 
 * (defined by their URIs).
 * 
 * @author Maarten Decat
 *
 */
public abstract class RemotePolicyEvaluatorModule {

	/**
	 * Returns true if the module supports finding policies based on a
     * request (ie, target matching). By default this method returns false.
	 */
	public abstract boolean isRequestSupported();
	
	/**
	 * Returns true if the module supports finding policies based on an
     * id reference (in a PolicySet). By default this method returns false.
	 */
	public abstract boolean isIdReferenceSupported();
	
	/**
	 * Returns whether this module supports the given remote policy id.
	 * Can only return true if this module supports id references.
	 */
	public abstract boolean supportsId(URI id);
	
	/**
	 * Initializes this module. Does not really do anything for now,
	 * kept for future use.
	 */
	public void init(RemotePolicyEvaluator evaluator) {
		// nothing to do yet
	}
	
	/**
	 * Finds and evaluates the appropriate policy based on the context.
	 * Only supported if isRequestSupported(context).
	 */
	public abstract Result findAndEvaluate(EvaluationCtx context);
	
	/**
	 * Finds the appropriate policy based on the id and evaluates it based on
	 * the context. Only supported if isIdReferenceSupported() and supportsId(id).
	 */
	public abstract Result findAndEvaluate(URI id, EvaluationCtx context);
}
