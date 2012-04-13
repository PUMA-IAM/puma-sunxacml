package com.sun.xacml;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.ctx.Result;

/**
 * Class used for representing a remote policy element. On evaluation,
 * the remote policy uses the PDP's RemotePolicyEvaluator to actually
 * evaluate this policy.
 * 
 * @author maartend
 *
 */
public class RemotePolicyReference extends AbstractPolicy {
	
    // the reference
    private URI id;

    // the logger we'll use for all messages
    private static final Logger logger =
        Logger.getLogger(PolicyReference.class.getName());

    /**
     * Initialize this new RemotePolicy with given id and RemotePolicyEvaluator.
     */
    public RemotePolicyReference(URI id) {
    	this.id = id;
    }

    /**
     * Creates an instance of a RemotePolicyReference with given
     * node and evaluator.
     * 
     * A RemotePolicyRefernce has no attributes (for now) and its contents
     * are the URI defining the policy to be retrieved.
     */
    public static RemotePolicyReference getInstance(Node root)
    			throws ParsingException {
    	try {
    		NamedNodeMap attributes = root.getAttributes();
    		Node node = attributes.getNamedItem("PolicyId");
    		String id = node.getTextContent();
			return new RemotePolicyReference(new URI(id));
		} catch (DOMException e) {
            throw new ParsingException("Invalid URI in RemotePolicyReference", e);
		} catch (URISyntaxException e) {
            throw new ParsingException("Invalid URI in RemotePolicyReference", e);
		}
    }

    /**
     * Returns the id of this policy.
     */
    public URI getId() {
        return id;
    }

    /**
     * 
     */
    public String getVersion() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    public CombiningAlgorithm getCombiningAlg() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    public String getDescription() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    public Target getTarget() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    public String getDefaultVersion() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
	public List getChildren() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
	public List getChildElements() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    @SuppressWarnings("rawtypes")
	public Set getObligations() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * 
     */
    public PolicyMetaData getMetaData() {
        throw new UnsupportedOperationException("I know this is very " +
        		"bad design, but this operation is not supported on a RemotePolicy.");
    }

    /**
     * This is a though one: we cannot really know whether the other party's
     * policy matches the request. We have two options: we can ask the other party
     * or just send the request.
     * 
     * We opt for the latter option: in case their policies do not match the request,
     * they should just return "InApplicable". The former option would just introduce
     * more complexity and a second request between both parties.
     * 
     * Therefore, this policy supports any EvaluationCtx.
     * 
     * @return new MatchResult(MatchResult.MATCH);
     */
    public MatchResult match(EvaluationCtx context) {
        return new MatchResult(MatchResult.MATCH);
    }

    /**
     * Tries to evaluate the policy by calling the combining algorithm on
     * the given policies or rules. The <code>match</code> method must always
     * be called first, and must always return MATCH, before this method
     * is called.
     *
     * @param context the representation of the request
     *
     * @return the result of evaluation
     */
    public Result evaluate(EvaluationCtx context) {
    	// immediate fail if we have not received an evaluator.
    	if(context.getRemotePolicyEvaluator() == null) {
    		logger.severe("No evaluator set when evaluating this RemotePolicyRefenerence.");
    		return new Result(Result.DECISION_INDETERMINATE);
    	}
    	
    	return context.getRemotePolicyEvaluator().findAndEvaluate(id, context);
    }

	@Override
	public void encode(OutputStream output) {
        PrintStream out = new PrintStream(output);
        out.println("<RemotePolicyReference id=\"" + this.getId().toString() + "\"");
	}

	@Override
	public void encode(OutputStream output, Indenter indenter) {
		// Not a correct implementation since we are not using the indenter,
		// but skrew it, proof of concept :)
		this.encode(output);		
	}

}
