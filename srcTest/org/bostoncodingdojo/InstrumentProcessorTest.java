package org.bostoncodingdojo;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstrumentProcessorTest {

	private TaskDispatcher dispatcher;
	private InstrumentProcessor processor;
	private String[] tasks;
	private Instrument instrument;
	private TestInstrumentListener instrumentListener;

	@Before
	public void before() {
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		// TODO Don't use default implementations, instead use mocks.
		dispatcher = new DefaultTaskDispatcher(tasks);
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
			// TODO assert Instrument.execute() is called with task.
		}
	}
	
	@Test
	public void testProcessFinishesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			// TODO assert TaskDispatcher.finishedTask() is called with task.
		}
	}
	
	@Test
	public void testExceptionsBubbleUpToCaller() throws Exception {
		// TODO make Instrument.execute() throw exceptions
		try {
			processor.process();
			fail();
		} catch (Throwable t) {
			// Success.
		}
	}
	
	@Test
	public void testErrorsAreWrittenToLog() throws Exception {
		// TODO
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
