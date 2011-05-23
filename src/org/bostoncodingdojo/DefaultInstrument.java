package org.bostoncodingdojo;

import java.util.ArrayList;
import java.util.List;

// Problem: We'd like it to be final, but we can't because the test needs to extend it.
class DefaultInstrument implements Instrument {
	
	List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
	
	@Override
	public void execute(final String task) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (InstrumentListener listener : listeners) {
					listener.taskFinished(task);
				}
			}
		}).start();
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
