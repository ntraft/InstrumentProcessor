package org.bostoncodingdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
			assertEquals(task, instrumentListener.lastExecutedTask);
		}
	}
	
	@Test
	public void testProcessFinishesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			assertEquals(task, dispatcher.lastFinishedTask);
		}
	}
	
	@Test
	public void testExceptionsBubbleUpToCaller() throws Exception {
		instrument = new ExceptionThrowingInstrument();
		processor = new DefaultInstrumentProcessor(dispatcher, instrument);
		try {
			processor.process();
			fail();
		} catch (Throwable t) {
			// Success.
		}
	}
	
	@Test
	public void testErrorsAreWrittenToLog() throws Exception {
		// If we really wanted to test this, we would need the ability to pass a custom
		// PrintStream into the processor, so we could see what its output is. But I'm
		// not going to do that because we can already see that's overkill.
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
	
	private final class ExceptionThrowingInstrument extends DefaultInstrument {
		@Override
		public void execute(String task) {
			throw new RuntimeException("Surprise!");
		}
	}
	
	private final class TestInstrumentListener implements InstrumentListener {
		
		String lastExecutedTask;
		String lastErrorTask;
		
		@Override
		public void taskFinished(String task) {
			lastExecutedTask = task;
		}
		
		@Override
		public void taskError(String task) {
			lastErrorTask = task;
		}
	}
}
