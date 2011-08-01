package org.bostoncodingdojo;

public interface TaskDispatcher {
	/**
	 * @return The next task to be processed.
	 */
	String getTask();
	/**
	 * Should be called when the {@link Instrument} has finished executing the given
	 * task.
	 * 
	 * @param task The task which is now finished.
	 */
	void finishedTask(String task);
}
