package org.bostoncodingdojo;

public interface Instrument {
	
	void execute(String task);
	
	void addInstrumentListener(InstrumentListener listener);
	void removeInstrumentListener(InstrumentListener listener);
}
