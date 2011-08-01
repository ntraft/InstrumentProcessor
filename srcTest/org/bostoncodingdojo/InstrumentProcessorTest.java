package org.bostoncodingdojo;

import org.junit.Test;

/**
 * This is the suite of tests that must be passed. The class under test is the
 * {@link DefaultInstrumentProcessor}. All your tests should be run against
 * that class.
 * </p><p>
 * Happy coding!
 * 
 * @author ntraft
 */
public class InstrumentProcessorTest {

	/**
	 * When <code>InstrumentProcessor.process()</code> is invoked, the Instrument's
	 * <code>execute()</code> method is invoked with the correct task.
	 */
	@Test
	public void invokingProcessTellsTheInstrumentToExecuteTheNextTask() throws Exception {
		// TODO
	}
	
	/**
	 * When the instrument fires the <code>InstrumentListener.taskFinished()</code>
	 * event, the <code>InstrumentProcessor</code> calls the TaskDispatcher's
	 * <code>finished()</code> method with the correct task.
	 */
	@Test
	public void taskFinishedEventsMarkTheTaskAsFinished() throws Exception {
		// TODO
	}
	
	/**
	 * When the Instrument's <code>execute()</code> method throws an exception, this
	 * exception is passed on to the caller of the <code>process()</code> method.
	 */
	@Test
	public void instrumentExecutionExceptionsBubbleUpToProcessCaller() throws Exception {
		// TODO
	}
	
	/**
	 * When the instrument fires the <code>InstrumentListener.taskError()</code> event,
	 * the instrument processor prints an error message containing the task name.
	 */
	@Test
	public void taskErrorEventsAreWrittenToErrorLogs() throws Exception {
		// TODO
	}
}
