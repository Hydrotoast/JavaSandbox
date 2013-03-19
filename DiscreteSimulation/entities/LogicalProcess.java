package entities;

import lang.EmptyListException;
import router.Content;
import router.QueueChannel;
import router.WaitQueue;
import events.TimeEvent;


public class LogicalProcess implements QueueChannel<TimeEvent> {
	private WaitQueue line;
	private int processTime;
	private boolean idle;
	
	public LogicalProcess(WaitQueue line, int processTime) {
		this.line = line;
		this.processTime = processTime;
		this.idle = true;
	}
	
	public int getProcessTime() {
		return processTime;
	}
	
	public boolean isIdle() {
		return idle;
	}

	public void engage() {
		idle = false;
	}
	
	public void disengage() {
		idle = true;
	}
	
	public void process() throws EmptyListException {
		if (isIdle()) {
			if (!line.isEmpty()) {
				line.dequeue();
				engage();
			} else {
				throw new EmptyListException();
			}
		}
	}

	@Override
	public int compareTo(QueueChannel<? extends Content> o) {
		if (line.getCongestion() > o.getCongestion())
			return 1;
		else if (line.getCongestion() < o.getCongestion())
			return -1;
		else
			return 0;
	}

	@Override
	public int getCongestion() {
		return line.getCongestion();
	}

	@Override
	public void dispatch(TimeEvent message) {
		line.dispatch(message);
	}
}
