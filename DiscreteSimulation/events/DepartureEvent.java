package events;

import router.Content;
import dispatcher.EventManager;
import entities.LogicalProcess;

public class DepartureEvent extends TimeEvent {
	private LogicalProcess process;

	public DepartureEvent(int time, EventManager manager, LogicalProcess process) {
		super(time, manager);
		this.process = process;
	}

	public LogicalProcess getLogicalProcess() {
		return process;
	}

	@Override
	public Class<? extends Content> getType() {
		return this.getClass();
	}
}
