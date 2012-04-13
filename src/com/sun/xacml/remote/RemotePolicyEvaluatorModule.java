package com.sun.xacml.remote;

import java.net.URI;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ctx.Result;

/**
 * Class used for representing a remote policy evaluator module.
 * Each module is capable of evaluating certain remote policies 
 * (defined by their URIs).
 * 
 * @author maartend
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
