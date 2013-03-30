package com.sun.xacml.ctx;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.cond.EvaluationResult;

/**
 * Class used for representing an attribute cache.
 * 
 * @author Maarten Decat
 * 
 */
public class AttributeCache {
	
	private Set<CachedResult> cachedResults = new HashSet<CachedResult>();

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
	public void cacheResult(URI type, URI id, URI issuer, URI category,
			int designatorType, EvaluationResult result) {
		this.cachedResults.add(new CachedResult(type, id, issuer, category, designatorType, result));
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
	public EvaluationResult getResult(URI type, URI id, URI issuer,
			URI category, int designatorType) {
		for(CachedResult result: this.cachedResults) {
			if(result.matches(type, id, issuer, category, designatorType)) {
				return result.getResult();
			}
		}
		return null;
	}
}
