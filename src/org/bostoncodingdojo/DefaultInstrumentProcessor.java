package org.bostoncodingdojo;

public class DefaultInstrumentProcessor implements InstrumentProcessor {
	
	private final TaskDispatcher dispatcher;

	DefaultInstrumentProcessor(TaskDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public void process() {
		dispatcher.finishedTask(dispatcher.getTask());
	}
	
}
