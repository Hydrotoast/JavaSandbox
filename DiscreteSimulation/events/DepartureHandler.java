package events;

import dispatcher.Clock;
import dispatcher.EventManager;
import entities.ProcessList;

public class DepartureHandler implements Handler {

//	@Override
//	public void update(Observable o) {
//		if (!o.getClass().isAssignableFrom(EventManager.class))
//			return;
//		
//		EventManager manager = (EventManager) o;
//		
//		if (manager.getCurrentEvent().getClass().isAssignableFrom(DepartureEvent.class)) {
//			DepartureEvent event = (DepartureEvent)manager.getCurrentEvent();
//			
//			System.out.println("Event complete at time " + Clock.getTime());
//		
//			//event.getLogicalProcess().release();
//			event.getLogicalProcess().disengange();
//			
//			/* Schedule conditional events */
//			ProcessList processes = manager.getProcesses();
//			processes.processAll();
//				
////			if (!WaitLine.getInstance().isEmpty()) {
////				manager.scheduleArrival(Clock.getTime());
////				WaitLine.getInstance().dequeue();
////			}
//		}
//	}

	private void scheduleConditionalEvents(EventManager manager) {
		ProcessList processes = manager.getProcesses();
		processes.processAll();
	}
	
	@Override
	public void dispatch(TimeEvent message) {
		EventManager manager = message.getManager();
		
		DepartureEvent event = (DepartureEvent)manager.getCurrentEvent();
		
		System.out.println("Event complete at time " + Clock.getTime());
	
		//event.getLogicalProcess().release();
		event.getLogicalProcess().disengage();
		
		scheduleConditionalEvents(manager);
	}

}
