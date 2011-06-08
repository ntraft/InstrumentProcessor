package org.bostoncodingdojo;

import org.bostoncodingdojo.log.Log;



public class DefaultInstrumentProcessor implements InstrumentProcessor {
	
	private final TaskDispatcher dispatcher;
	private final Instrument instrument;

	DefaultInstrumentProcessor(TaskDispatcher dispatcher, Instrument instrument) {
		this.dispatcher = dispatcher;
		this.instrument = instrument;
		instrument.addInstrumentListener(new InstrumentListener() {
			@Override
			public void taskFinished(String task) {
				DefaultInstrumentProcessor.this.dispatcher.finishedTask(task);
			}
			
			@Override
			public void taskError(String task) {
				Log.error("Error executing task: " + task);
			}
		});
	}

	@Override
	public void process() {
		final String task = dispatcher.getTask();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {}
				instrument.execute(task);
			}
		}).start();
	}
	
}
