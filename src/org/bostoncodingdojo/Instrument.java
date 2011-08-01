package org.bostoncodingdojo;

public interface Instrument {
	/**
	 * Execute the given task. Fires a {@link InstrumentListener#taskFinished(String)}
	 * event when the task is completed.
	 * 
	 * @param task The task to execute.
	 */
	void execute(String task);
	
	void addInstrumentListener(InstrumentListener listener);
	void removeInstrumentListener(InstrumentListener listener);
}
