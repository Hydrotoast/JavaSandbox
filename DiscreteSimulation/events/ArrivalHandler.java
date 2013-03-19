package events;

import router.ProcessRouter;
import dispatcher.Clock;
import dispatcher.EventManager;

public class ArrivalHandler implements Handler {

	// @Override
	// public void update(Observable o) {
	// if (!o.getClass().isAssignableFrom(EventManager.class))
	// return;
	//
	// EventManager manager = (EventManager) o;
	//
	// /* Schedule following events */
	// if (manager.getCurrentEvent().getClass()
	// .isAssignableFrom(ArrivalEvent.class)) {
	// /* Process B-Events */
	// ProcessRouter.getInstance().dispatch(manager.getCurrentEvent());
	//
	// /* Process C-Events */
	// manager.getProcesses().processAll();
	//
	// // try {
	// // LogicalProcess process = (LogicalProcess) EntityPool
	// // .getInstance().acquireResource();
	// //
	// // manager.scheduleDeparture(
	// // Clock.getTime() + process.getProcessTime(), process);
	// //
	// System.out.println("Queued up Event " + Clock.getTime());
	// // } catch (EmptyPoolException ex) {
	// // /* Queue the event for later */
	// // System.out.println("No processes available; initiate delay at "
	// // + Clock.getTime());
	// //
	// // WaitLine.getInstance().enqueue(manager.getCurrentEvent());
	// // }
	// }
	// }
	private void scheduleBoundEvents(EventManager manager) {
		ProcessRouter.getInstance().dispatch(manager.getCurrentEvent());
	}

	private void scheduleConditionalEvents(EventManager manager) {
		manager.getProcesses().processAll();
	}

	@Override
	public void dispatch(TimeEvent message) {
		EventManager manager = message.getManager();

		/* Schedule following events */
		scheduleBoundEvents(manager);
		scheduleConditionalEvents(manager);

		System.out.println("Queued up Event " + Clock.getTime());
	}
}
