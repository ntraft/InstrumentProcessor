package org.bostoncodingdojo;


public class SpecialInstrumentProcessor implements InstrumentProcessor {

	private final DefaultTaskDispatcher dispatcher;
	private final DefaultInstrument instrument;

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
	
	final DefaultInstrument getInstrument() {
		return instrument;
	}
	
	final DefaultTaskDispatcher getDispatcher() {
		return dispatcher;
	}

	@Override
	public void process() {
		String task = dispatcher.getTask();
		instrument.execute(task);
	}
	
}
