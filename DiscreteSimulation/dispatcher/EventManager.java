package dispatcher;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import lang.EmptyHeapException;
import router.Channel;
import router.DynamicRouter;
import router.ProcessRouter;
import util.MinHeapPriorityQueue;
import entities.LogicalProcess;
import entities.ProcessList;
import events.ArrivalEvent;
import events.DepartureEvent;
import events.Handler;
import events.TimeEvent;

public class EventManager implements DynamicRouter<TimeEvent> {
	private MinHeapPriorityQueue<TimeEvent> pendingQueue;
	private Map<Class<? extends TimeEvent>, Handler> observers;
	private boolean isDone;

	private ProcessList processes;
	private TimeEvent currentEvent;

	public EventManager() {
		pendingQueue = new MinHeapPriorityQueue<TimeEvent>(
				new Comparator<TimeEvent>() {
					@Override
					public int compare(TimeEvent op1, TimeEvent op2) {
						return op1.compareTo(op2);
					}
				});

		observers = new HashMap<Class<? extends TimeEvent>, Handler>();

		isDone = false;

		processes = new ProcessList(this);
		currentEvent = null;
	}

	public ProcessList getProcesses() {
		return processes;
	}

	public void registerProcess(LogicalProcess process) {
		processes.addFirst(process);
		ProcessRouter.getInstance().registerChannel(process);
	}

	public TimeEvent getCurrentEvent() {
		return currentEvent;
	}

	public void scheduleArrival(int time) {
		pendingQueue.enqueue(new ArrivalEvent(time, this));
	}

	public void scheduleDeparture(int time, LogicalProcess lp) {
		pendingQueue.enqueue(new DepartureEvent(time, this, lp));
	}

	public void run() {
		while (!isDone) {
			try {
				/* Pop the next event */
				currentEvent = pendingQueue.peek();

				/* Set time discretely */
				Clock.setTime(currentEvent.getTime());

				/* Dispatch events for bound and conditional activity */
				dispatch(currentEvent);
				pendingQueue.dequeue();
			} catch (EmptyHeapException e) {
				isDone = true;
			}
		}
	}

	@Override
	public void registerChannel(Class<? extends TimeEvent> contentType,
			Channel<? extends TimeEvent> channel) {
		observers.put(contentType, (Handler)channel);
	}

	@Override
	public void dispatch(TimeEvent content) {
		observers.get(content.getClass()).dispatch(content);
	}
}
