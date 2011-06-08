package org.bostoncodingdojo;

public class DefaultTaskDispatcher implements TaskDispatcher {

	private final String[] tasks;
	private int index = 0;
	Object lastFinishedTask;

	DefaultTaskDispatcher(String[] tasks) {
		this.tasks = tasks;
	}

	@Override
	public String getTask() {
		return tasks[index++];
	}
	
	@Override
	public void finishedTask(String task) {
		lastFinishedTask = task;
	}
	
}
