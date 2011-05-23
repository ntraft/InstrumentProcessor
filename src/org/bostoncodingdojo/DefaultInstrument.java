package org.bostoncodingdojo;

import java.util.ArrayList;
import java.util.List;

final class DefaultInstrument implements Instrument {
	
	List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
	
	@Override
	public void execute(final String task) {
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
