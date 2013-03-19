package events;

import router.Content;
import dispatcher.EventManager;

public class ArrivalEvent extends TimeEvent {

	public ArrivalEvent(int time, EventManager manager) {
		super(time, manager);
	}

	@Override
	public Class<? extends Content> getType() {
		return this.getClass();
	}
}
