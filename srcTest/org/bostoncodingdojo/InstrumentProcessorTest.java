package org.bostoncodingdojo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstrumentProcessorTest {
	
	private TaskDispatcher dispatcher;
	private InstrumentProcessor processor;
	private String[] tasks;

	@Before
	public void before() {
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		dispatcher = new DefaultTaskDispatcher(tasks);
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
			// TODO
		}
	}
}
