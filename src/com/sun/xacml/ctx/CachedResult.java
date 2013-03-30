package com.sun.xacml.ctx;

import java.net.URI;

import com.sun.xacml.cond.EvaluationResult;

/**
 * Class used for representing a chached evaluation result.
 * 
 * @author Maarten Decat
 * 
 */
public class CachedResult {
	
	private URI type;
	
	private URI id;
	
	private URI issuer;
	
	private URI category;
	
	private int designatorType;
	
	private EvaluationResult result;
	
	public EvaluationResult getResult() {
		return this.result;
	}

	public CachedResult(URI type, URI id, URI issuer, URI category,
			int designatorType, EvaluationResult result) {
		this.type = type;
		this.id = id;
		this.issuer = issuer;
		this.category = category;
		this.designatorType = designatorType;
		this.result = result;
	}
	
	public boolean matches(URI type, URI id, URI issuer, URI category,
			int designatorType) {
		return this.designatorType == designatorType
				&& URIsMatch(this.type, type)
				&& URIsMatch(this.id, id)
				&& URIsMatch(this.issuer, issuer)
				&& URIsMatch(this.category, category);
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
