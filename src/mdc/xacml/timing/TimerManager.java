package mdc.xacml.timing;

/**
 * Class used for managing timers. 
 * 
 * @author Maarten Decat
 *
 */
public abstract class TimerManager {
	
	/***************************
	 * SINGLETON STUFF
	 */
	
	private static TimerManager instance;
	
	/**
	 * Returns the instance of the TimerManager.
	 * Default value (if not overridden by setInstance()): DefaultTimerManager.
	 * @return
	 */
	public static TimerManager getInstance() {
		if(instance == null) {
			instance = new DefaultTimerManager();
		}
		return instance;
	}
	
	/**
	 * This class allows to set the timer manager instance for control from
	 * outside.
	 * 
	 * @param instance
	 */
	public static void setInstance(TimerManager i) {
		instance = i;
	}
	
	/****************************
	 * FUNCTIONALITY
	 */
	
	public abstract TimerContext getTargetMatchTimer(String policyId);
	
	public abstract TimerContext getPolicyEvaluationTimer(String policyId);

}
