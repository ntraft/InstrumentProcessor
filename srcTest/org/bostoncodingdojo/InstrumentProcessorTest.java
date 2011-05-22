package org.bostoncodingdojo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstrumentProcessorTest {
	
	private TestTaskDispatcher dispatcher;
	private InstrumentProcessor processor;
	private String[] tasks;

	@Before
	public void before() {
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		dispatcher = new TestTaskDispatcher(tasks);
		processor = new DefaultInstrumentProcessor(dispatcher);
	}
	
	@After
	public void after() {
		tasks = null;
		dispatcher = null;
		processor = null;
	}
	
	@Test
	public void testProcessExecutesNextTask() throws Exception {
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
}
