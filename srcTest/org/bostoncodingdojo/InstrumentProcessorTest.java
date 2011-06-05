package org.bostoncodingdojo;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InstrumentProcessorTest {

//	@Test
//	public void testProcessExecutesNextTask() throws Exception {
//	}
//
//	@Test
//	public void testProcessFinishesNextTask() throws Exception {
//	}
//
//	@Test
//	public void testExceptionsBubbleUpToCaller() throws Exception {
//	}
//
//	@Test
//	public void testErrorsAreWrittenToLog() throws Exception {
//	}
	
	@Test
	public void testAllTasksAreExecuted() throws Exception {
		// Discuss: Is this good enough? From the fact that all tasks have been
		// "finished," is it reasonable to assume that all tasks were also "executed?"
		TestTaskDispatcher dispatcher = new TestTaskDispatcher();
		InstrumentProcessor proc = new DefaultInstrumentProcessor(dispatcher, new TestInstrument());
		for (int i=0; i<TestTaskDispatcher.tasks.length; i++) {
			proc.process();
		}
		dispatcher.allTasksFinished();
	}
	
	private static class TestTaskDispatcher implements TaskDispatcher {
		
		private static final String[] tasks = new String[]{"amazing", "confound", "paraphrase", "junior", "ferrari"};
		private final List<String> finished = new ArrayList<String>();
		int count = 0;

		@Override
		public String getTask() {
			return tasks[count++];
		}

		@Override
		public void finishedTask(String task) {
			finished.add(task);
		}
		
		private void allTasksFinished() {
			// Problem: Tests that all tasks are finished, but does not test that each
			// was finished only once.
			for (String task : tasks) {
				assertTrue(finished.contains(task));
			}
		}
		
	}
	
	private class TestInstrument implements Instrument {

		private final List<InstrumentListener> listeners = new ArrayList<InstrumentListener>();
		
		@Override
		public void execute(String task) {
			for (InstrumentListener listener : listeners) {
				listener.taskFinished(task);
			}
		}

		@Override
		public void addInstrumentListener(InstrumentListener listener) {
			listeners.add(listener);
		}

		@Override
		public void removeInstrumentListener(InstrumentListener listener) {
			listeners.remove(listener);
		}
		
	}
}
