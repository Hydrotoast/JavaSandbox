package EventManager;

import edu.giocc.util.DynamicObservable;
import edu.giocc.util.DynamicObserver;

public class DepartureHandler implements DynamicObserver {

	@Override
	public void update(DynamicObservable o) {
		if (!o.getClass().isAssignableFrom(EventManager.class))
			return;

		EventManager manager = (EventManager) o;

		if (manager.getCurrentEvent().getClass()
				.isAssignableFrom(DepartureEvent.class))
			System.out.println("Event complete at time "
					+ manager.getCurrentEvent().getTime());
	}

}
