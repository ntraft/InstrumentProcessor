package org.bostoncodingdojo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstrumentProcessorTest {

	private TestTaskDispatcher dispatcher;
	private InstrumentProcessor processor;
	private String[] tasks;
	private DefaultInstrument instrument;
	private TestInstrumentListener instrumentListener;

	@Before
	public void before() {
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		dispatcher = new TestTaskDispatcher(tasks);
		instrument = new DefaultInstrument();
		instrumentListener = new TestInstrumentListener();
		instrument.addInstrumentListener(instrumentListener);
		// Problem: I have to pass in the objects I want it to delegate to, so I can
		// confirm that they were actually used. Would I still have this problem if I
		// were using a mocking framework?
		processor = new DefaultInstrumentProcessor(dispatcher, instrument);
	}
	
	@After
	public void after() {
		tasks = null;
		dispatcher = null;
		instrument = null;
		instrumentListener = null;
		processor = null;
	}
	
	@Test
	public void testProcessExecutesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			assertEquals(task, instrumentListener.lastExecutesTask);
		}
	}
	
	@Test
	public void testProcessFinishesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			assertEquals(task, dispatcher.lastFinishedTask);
		}
	}

	private final class TestTaskDispatcher extends DefaultTaskDispatcher {
		
		private String lastFinishedTask;

		TestTaskDispatcher(String[] tasks) {
			super(tasks);
		}
		
		@Override
		public void finishedTask(String task) {
			super.finishedTask(task);
			lastFinishedTask = task;
		}
	}
	
	private final class TestInstrumentListener implements InstrumentListener {
		
		String lastExecutesTask;
		String lastErrorTask;
		
		@Override
		public void taskFinished(String task) {
			lastExecutesTask = task;
		}
		
		@Override
		public void taskError(String task) {
			lastErrorTask = task;
		}
	}
}
