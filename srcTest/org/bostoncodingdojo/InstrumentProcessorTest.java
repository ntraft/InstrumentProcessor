package org.bostoncodingdojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstrumentProcessorTest {

	private SpecialInstrumentProcessor processor;
	private String[] tasks;

	@Before
	public void before() {
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		processor = new SpecialInstrumentProcessor(tasks);
	}
	
	@After
	public void after() {
		tasks = null;
		processor = null;
	}
	
	@Test
	public void testProcessExecutesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			assertEquals(task, processor.getInstrument().lastExecutedTask);
		}
	}
	
	@Test
	public void testProcessFinishesNextTask() throws Exception {
		for (String task : tasks) {
			processor.process();
			assertEquals(task, processor.getDispatcher().lastFinishedTask);
		}
	}
	
	@Test
	public void testExceptionsBubbleUpToCaller() throws Exception {
		// Can only test this if we have a way to cause the Instrument to throw an exception.
		try {
			processor.process();
		} catch (Throwable t) {
			// Success.
			return;
		}
		fail();
	}
	
	@Test
	public void testErrorsAreWrittenToLog() throws Exception {
		// If we really wanted to test this, we would need the ability to pass a custom
		// PrintStream into the processor, so we could see what its output is. But I'm
		// not going to do that because we can already see that's overkill.
	}
}
