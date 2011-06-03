package org.bostoncodingdojo.log;

public class DefaultLogger implements Logger {
	
	@Override
	public void info(String message) {
		System.out.println(message);
	}
	
	@Override
	public void error(String message) {
		System.err.println(message);
	}
	
	@Override
	public void error(String message, Throwable t) {
		System.err.println(message);
		t.printStackTrace();
	}
}
