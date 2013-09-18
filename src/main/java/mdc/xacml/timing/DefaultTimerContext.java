package mdc.xacml.timing;

/**
 * The default implementation for the timer context that does nothing.
 * 
 * @author Maarten Decat
 *
 */
public class DefaultTimerContext implements TimerContext {

	@Override
	public void stop() {
		// do nothing
	}

}
