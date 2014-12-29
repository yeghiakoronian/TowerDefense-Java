package infrastructure.loggin;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.contract.ITDLogger;

public class Log4jLogger implements ITDLogger {

	private static final Logger logger = LogManager.getLogger(Log4jLogger.class.getName());
	@Override
	public void writer(Object message, Throwable t) {
		logger.error(message, t);
	}

}
