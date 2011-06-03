package org.bostoncodingdojo.log;

public interface Logger {

	void info(String message);

	void error(String message);

	void error(String message, Throwable t);
	
}
