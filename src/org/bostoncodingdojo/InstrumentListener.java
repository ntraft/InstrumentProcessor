package org.bostoncodingdojo;

import java.util.EventListener;

public interface InstrumentListener extends EventListener {
	void taskFinished(String task);
	void taskError(String task);
}
