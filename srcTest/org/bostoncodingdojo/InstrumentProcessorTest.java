package org.bostoncodingdojo;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bostoncodingdojo.log.Log;
import org.bostoncodingdojo.log.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class InstrumentProcessorTest {

	private TaskDispatcher dispatcher;
	private InstrumentProcessor processor;
	private String[] tasks;
	private Instrument instrument;
	private InstrumentListener instrumentListener;

	@Before
	public void before() {
		// TODO Could probably clean this up with @Mock annotations.
		tasks = new String[]{"jocular", "infinite", "drastic", "popsicle", "comeback"};
		
		// TaskDispatcher returns 5 tasks, in this order.
		dispatcher = mock(TaskDispatcher.class);
		when(dispatcher.getTask()).thenReturn("jocular", "infinite", "drastic", "popsicle", "comeback");
		
		// When a processor adds its listener to the instrument, we'll cache it so we
		// can call methods on it later.
		instrument = mock(Instrument.class);
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				instrumentListener = (InstrumentListener) invocation.getArguments()[0];
				return null;
			}
		}).when(instrument).addInstrumentListener(any(InstrumentListener.class));
		
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
		for (int i=0; i<tasks.length; i++) {
			processor.process();
		}
		// We want verification to happen at the VERY end. If I verified in each
		// iteration of the loop, then I can't be sure that each string was only used
		// once.
		for (String task : tasks) {
			verify(instrument).execute(task);
			// Don't want it to call finishedTask prematurely. It should only call this
			// when it receives the finished event.
			verify(dispatcher, never()).finishedTask(anyString());
		}
	}
	
	@Test
	public void testProcessFinishesNextTask() throws Exception {
		// When a task is executed, the instrument fires the taskFinished event.
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				instrumentListener.taskFinished((String) invocation.getArguments()[0]);
				return null;
			}
			
		}).when(instrument).execute(anyString());
		
		for (int i=0; i<tasks.length; i++) {
//			instrument.execute(tasks[i]);
			processor.process();
		}
		Thread.sleep(6000);
		for (String task : tasks) {
			verify(dispatcher).finishedTask(task);
		}
	}
	
	@Test
	public void testExceptionsBubbleUpToCaller() throws Exception {
		doThrow(new RuntimeException()).when(instrument).execute(anyString());
		
		try {
			processor.process();
		} catch (Throwable t) {
			// Success.
			return;
		}
		
		// Didn't throw.
		fail();
	}
	
	@Test
	public void testErrorsAreWrittenToLog() throws Exception {
		Logger logger = mock(Logger.class);
		Log.setDefaultLogger(logger);
		
		// When a task is executed, the instrument fires the taskError event.
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				instrumentListener.taskError(((String) invocation.getArguments()[0]));
				return null;
			}
			
		}).when(instrument).execute(anyString());
		
		for (String task : tasks) {
			instrument.execute(task);
		}
		for (String task : tasks) {
			verify(logger).error(contains(task));
		}
	}
}
