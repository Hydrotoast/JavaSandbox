package EventManager;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import edu.giocc.lang.EmptyHeapException;
import edu.giocc.util.DynamicObservable;
import edu.giocc.util.DynamicObserver;
import edu.giocc.util.ObservableEvent;
import edu.giocc.util.queue.MaxHeapPriorityQueue;

public class EventManager implements DynamicObservable {
	private MaxHeapPriorityQueue<TimeEvent> pendingQueue;
	// private ArrayList<Observer> observers;
	private Map<Class<? extends ObservableEvent>, DynamicObserver> observers;

	private TimeEvent currentEvent;
	private boolean isDone;

	public EventManager() {
		pendingQueue = new MaxHeapPriorityQueue<TimeEvent>(
				new Comparator<TimeEvent>() {
					@Override
					public int compare(TimeEvent op1, TimeEvent op2) {
						return op1.compareTo(op2);
					}
				});

		observers = new HashMap<Class<? extends ObservableEvent>, DynamicObserver>();

		isDone = false;
	}

	public TimeEvent getCurrentEvent() {
		return currentEvent;
	}

	public void scheduleArrival(int time) {
		pendingQueue.enqueue(new ArrivalEvent(time));
	}

	public void scheduleDeparture(int time) {
		pendingQueue.enqueue(new DepartureEvent(time));
	}

	@Override
	public void registerObserver(Class<? extends ObservableEvent> e,
			DynamicObserver o) {
		observers.put(e, o);
	}

	@Override
	public void updateAll(Class<? extends ObservableEvent> e) {
		observers.get(e).update(this);
	}

	public void run() {
		while (!isDone) {
			try {
				currentEvent = pendingQueue.peek();
				updateAll(currentEvent.getClass());
				pendingQueue.dequeue();
			} catch (EmptyHeapException e) {
				isDone = true;
			}
		}
	}
}
