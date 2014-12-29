package core.contract;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITDLogger.
 */
public interface ITDLogger {
	
	/**
	 * Writer  interface for logging this interface will be used for IOC and dependency injection
	 *
	 * @param message log message
	 * @param t throwable
	 */
	public void writer(Object message, Throwable t);

}
