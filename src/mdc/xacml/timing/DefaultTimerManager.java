package mdc.xacml.timing;

/**
 * The default timer manager that does nothing and therefore just returns 
 * DefaultTimerContext instances.
 * 
 * @author Maarten Decat
 *
 */
public class DefaultTimerManager extends TimerManager {

	/**
	 * Just returns a DefaultTimerContext on everything.
	 */
	@Override
	public TimerContext getPolicyEvaluationTimer(String policyId) {
		return new DefaultTimerContext();
	}

	/**
	 * Just returns a DefaultTimerContext on everything.
	 */
	@Override
	public TimerContext getTargetMatchTimer(String policyId) {
		return new DefaultTimerContext();
	}
	
	

}
