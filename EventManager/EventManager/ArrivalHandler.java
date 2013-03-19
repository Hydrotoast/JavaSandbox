package EventManager;

import edu.giocc.util.DynamicObservable;
import edu.giocc.util.DynamicObserver;

public class ArrivalHandler implements DynamicObserver {

	@Override
	public void update(DynamicObservable o) {
		if (!o.getClass().isAssignableFrom(EventManager.class))
			return;

		EventManager manager = (EventManager) o;

		/* Create entities */

		/* Schedule following events */
		if (manager.getCurrentEvent().getClass()
				.isAssignableFrom(ArrivalEvent.class))
			manager.scheduleDeparture(manager.getCurrentEvent().getTime() + 50);
	}

}
