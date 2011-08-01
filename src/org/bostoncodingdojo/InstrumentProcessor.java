package org.bostoncodingdojo;

public interface InstrumentProcessor {
	/**
	 * Takes the next task from the {@link TaskDispatcher} and asks the
	 * {@link Instrument} to process the task.
	 */
	void process();
}
