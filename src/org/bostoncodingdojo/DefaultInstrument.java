package org.bostoncodingdojo;

import java.util.ArrayList;
import java.util.List;

// Problem: We'd like it to be final, but we can't because the test needs to extend it.
class DefaultInstrument implements Instrument {
	
	List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
	Object lastExecutedTask;
	
	@Override
	public void execute(final String task) {
		lastExecutedTask = task;
		for (InstrumentListener listener : listeners) {
			listener.taskFinished(task);
		}
	}
	
	@Override
	public void addInstrumentListener(InstrumentListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeInstrumentListener(InstrumentListener listener) {
		listeners.remove(listener);
	}
	
}
