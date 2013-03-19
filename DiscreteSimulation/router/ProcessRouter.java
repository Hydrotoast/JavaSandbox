package router;


import java.util.Comparator;

import util.MinHeapPriorityQueue;
import entities.LogicalProcess;
import events.TimeEvent;


public class ProcessRouter implements Router<TimeEvent> {
	private static ProcessRouter instance;
	private MinHeapPriorityQueue<QueueChannel<TimeEvent>> channels;
	
	private ProcessRouter() {
		channels = new MinHeapPriorityQueue<QueueChannel<TimeEvent>>(new Comparator<QueueChannel<TimeEvent>>() {

			@Override
			public int compare(QueueChannel<TimeEvent> op1, QueueChannel<TimeEvent> op2) {
				if (op1.compareTo(op2) > 0)
					return 1;
				else if (op1.compareTo(op2) < 0)
					return -1;
				else
					return 0;
			}
			
		});
	}
	
	public static ProcessRouter getInstance() {
		if (instance == null)
			instance = new ProcessRouter();
		return instance;
	}

	@Override
	public void registerChannel(QueueChannel<? extends TimeEvent> channel) {
		channels.enqueue((LogicalProcess) channel);
	}
	
	@Override
	public void dispatch(TimeEvent timeEvent) {
		/* Dispatch message to the least congested route */
		QueueChannel<TimeEvent> channel = channels.peek();
		channel.dispatch(timeEvent);
		
		/* Dequeue and Enqueue to reheapify */
		channels.dequeue();
		channels.enqueue(channel);
	}
}
