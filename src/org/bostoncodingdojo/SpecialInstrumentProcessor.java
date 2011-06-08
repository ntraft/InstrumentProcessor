package org.bostoncodingdojo;


public class SpecialInstrumentProcessor implements InstrumentProcessor {

	private final TaskDispatcher dispatcher;
	private final Instrument instrument;

	SpecialInstrumentProcessor(String[] tasks) {
		dispatcher = new DefaultTaskDispatcher(tasks);
		instrument = new DefaultInstrument();
		instrument.addInstrumentListener(new InstrumentListener() {
			@Override
			public void taskFinished(String task) {
				SpecialInstrumentProcessor.this.dispatcher.finishedTask(task);
			}
			
			@Override
			public void taskError(String task) {
				System.err.println("Error executing task: " + task);
			}
		});
	}
	
	Instrument getInstrument() {
		return instrument;
	}
	
	final TaskDispatcher getDispatcher() {
		return dispatcher;
	}

	@Override
	public void process() {
		String task = dispatcher.getTask();
		instrument.execute(task);
	}
	
}
