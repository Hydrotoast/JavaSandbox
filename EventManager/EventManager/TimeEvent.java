package EventManager;

import edu.giocc.util.ObservableEvent;

public abstract class TimeEvent implements ObservableEvent,
		Comparable<TimeEvent> {
	private int time;

	TimeEvent(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
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
