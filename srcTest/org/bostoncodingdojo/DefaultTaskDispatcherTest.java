package org.bostoncodingdojo;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefaultTaskDispatcherTest {
	
	@Test
	public void testTasksReturnedInOrder() throws Exception {
		// Problem: We need a TaskDispatcher implementation. Do we make an entire
		// implementation just for the purposes of this test suite? Do we use a 
		// "default" implementation? If so, what happens when someone changes the 
		// default implementation? Will the test break, even though the thing that's 
		// broken is the TaskDispatcher, not the InstrumentProcessor?
		String[] tasks = new String[]{"bananas", "in", "my", "shorts"};
		DefaultTaskDispatcher dispatcher = new DefaultTaskDispatcher(tasks);
		for (int i=0; i<tasks.length; i++) {
			assertEquals(tasks[i], dispatcher.getTask());
		}
	}
}
