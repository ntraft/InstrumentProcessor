package org.bostoncodingdojo;

import java.util.EventListener;

public interface InstrumentListener extends EventListener {
	/**
	 * Called when the given task has completed successfully.
	 * 
	 * @param task The task which is now finished.
	 */
	void taskFinished(String task);
	/**
	 * Called when there is an error executing the given task.
	 * 
	 * @param task The task which caused an error.
	 */
	void taskError(String task);
}
