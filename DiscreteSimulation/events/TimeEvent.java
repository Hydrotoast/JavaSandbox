package events;

import router.Content;
import dispatcher.EventManager;

public abstract class TimeEvent implements Content, Comparable<TimeEvent> {
	private int time;
	private EventManager manager;
	
	TimeEvent(int time, EventManager manager) {
		this.time = time;
		this.manager = manager;
	}

	public int getTime() {
		return time;
	}
	
	public EventManager getManager() {
		return manager;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public int compareTo(TimeEvent other) {
		if (time > other.time)
			return 1;
		else if (time < other.time)
			return -1;
		else
			return 0;
	}
}
