package org.bostoncodingdojo.log;

public class Log {
	
	private static Logger logger = new DefaultLogger();
	
	static {
		try {
			String className = System.getProperty("logger.class.name");
			if (className != null) {
				@SuppressWarnings("unchecked")
				Class<? extends Logger> loggerClass = (Class<? extends Logger>) Class.forName(className);
				if (loggerClass != null) {
					logger = loggerClass.newInstance();
				}
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	public static void setDefaultLogger(Logger defaultLogger) {
		logger = defaultLogger;
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void error(String message, Throwable t) {
		logger.error(message, t);
	}
}