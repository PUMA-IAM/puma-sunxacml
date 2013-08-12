package mdc.xacml.timing;

/**
 * Interface representing a timer context (a single instance of a timer which has been
 * started before).
 * 
 * @author Maarten Decat
 *
 */
public interface TimerContext {
	
	/**
	 * Stops this timer context
	 */
	public void stop();

}
