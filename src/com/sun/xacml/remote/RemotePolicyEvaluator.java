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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ctx.Result;

/**
 * Class used for representing a remote policy evaluator. A remote policy
 * evaluator is constructed similar to a AttrbiteFinder or PolicyFinder:
 * it contains multiple RemotePolicyEvaluatorModules. 
 * 
 * Similar to a PolicyFinder, a RemotePolicyEvaluator can find/evaluate
 * policies based on their id (URI) or based on the request itself.
 * 
 * @author Maarten Decat
 *
 */
public class RemotePolicyEvaluator {

    // all modules in this finder
    private Set<RemotePolicyEvaluatorModule> allModules;

    // all the request modules
    private Set<RemotePolicyEvaluatorModule> requestModules;

    // all the reference modules
    private Set<RemotePolicyEvaluatorModule> referenceModules;

    // the logger we'll use for all messages
    private static final Logger logger =
        Logger.getLogger(RemotePolicyEvaluator.class.getName());
    
    /**
     * Default constructor: empty modules.
     */
    public RemotePolicyEvaluator() {
    	this.allModules = new HashSet<RemotePolicyEvaluatorModule>();
    	this.requestModules = new HashSet<RemotePolicyEvaluatorModule>();
    	this.referenceModules = new HashSet<RemotePolicyEvaluatorModule>();
    }

    /**
     * Returns the unordered <code>Set</code> of
     * <code>PolicyFinderModule</code>s used by this class to find policies.
     *
     * @return a <code>Set</code> of <code>PolicyFinderModule</code>s
     */
    public Set<RemotePolicyEvaluatorModule> getModules() {
        return new HashSet<RemotePolicyEvaluatorModule>(allModules);
    }

    /**
     * Sets the unordered <code>Set</code> of <code>PolicyFinderModule</code>s
     * used by this class to find policies.
     *
     * @param modules a <code>Set</code> of <code>PolicyFinderModule</code>s
     */
    public void setModules(Set<RemotePolicyEvaluatorModule> modules) {
        Iterator<RemotePolicyEvaluatorModule> it = modules.iterator();

        allModules = new HashSet<RemotePolicyEvaluatorModule>(modules);
        requestModules = new HashSet<RemotePolicyEvaluatorModule>();
        referenceModules = new HashSet<RemotePolicyEvaluatorModule>();

        while (it.hasNext()) {
        	RemotePolicyEvaluatorModule module = it.next();

            if (module.isRequestSupported())
                requestModules.add(module);

            if (module.isIdReferenceSupported())
                referenceModules.add(module);
        }
    }

    /**
     * Initializes all modules. Does not really do anything, implemented for future use.
     */
    public void init() {
        logger.finer("Initializing RemotePolicyEvalutor");

        Iterator<RemotePolicyEvaluatorModule> it = allModules.iterator();

        while (it.hasNext()) {
            RemotePolicyEvaluatorModule module = (it.next());
            module.init(this);
        }
    }

    /**
     * Finds and evaluates a policy based on the EvaluationCtx.
     */
    public Result findAndEvaluate(EvaluationCtx context) {
    	// for now, we do not do this, just return something
    	return new Result(Result.DECISION_NOT_APPLICABLE);
    }

    /**
     * Finds and evaluates a policy based on its id (evaluating the policy
     * still needs the context of course).
     */
    public Result findAndEvaluate(URI id, EvaluationCtx context) throws IllegalArgumentException {
    	for(RemotePolicyEvaluatorModule module: referenceModules) {
    		if(module.supportsId(id)) {
    			// in theory it can be that multiple modules support this 
    			// id. We should do something intelligent in that case (such
    			// as deciding we cannot decide on the request). For now, let's
    			// just return the first result we can find, that's more that enough.
    			return module.findAndEvaluate(id, context);
    		}
    	}
    	
    	// if we did not find any matching modules.
    	return new Result(Result.DECISION_INDETERMINATE);
    }

}