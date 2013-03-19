package router;

import lang.EmptyQueueException;
import util.ListQueue;
import events.TimeEvent;

public class WaitQueue implements QueueChannel<TimeEvent> {
	private ListQueue<TimeEvent> queue;

	public WaitQueue() {
		queue = new ListQueue<TimeEvent>();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public int size() {
		return queue.size();
	}
	
	public void enqueue(TimeEvent message) {
		queue.enqueue(message);
	}
	
	public TimeEvent peek() throws EmptyQueueException {
		return queue.peek();
	}
	
	public void dequeue() throws EmptyQueueException {
		queue.dequeue();
	}

	@Override
	public int compareTo(QueueChannel<? extends Content> o) {
		if (this.getCongestion() < o.getCongestion())
			return 1;
		else if (this.getCongestion() > o.getCongestion())
			return -1;
		else
			return 0;
	}

	@Override
	public int getCongestion() {
		return size();
	}

	@Override
	public void dispatch(TimeEvent message) {
		enqueue(message);
	}
}
