package dispatcher;

import router.WaitQueue;
import entities.LogicalProcess;
import events.ArrivalEvent;
import events.ArrivalHandler;
import events.DepartureEvent;
import events.DepartureHandler;

public class Program {	
	public static void main(String[] args) {
		EventManager dispatcher = new EventManager();		
		dispatcher.registerChannel(ArrivalEvent.class, new ArrivalHandler());
		dispatcher.registerChannel(DepartureEvent.class, new DepartureHandler());
		
		WaitQueue single = new WaitQueue();
//		dispatcher.registerProcess(new LogicalProcess(single, 13));
//		dispatcher.registerProcess(new LogicalProcess(single, 5));
		
		dispatcher.registerProcess(new LogicalProcess(new WaitQueue(), 13));
		dispatcher.registerProcess(new LogicalProcess(new WaitQueue(), 5));
		
		dispatcher.scheduleArrival(5);
		dispatcher.scheduleArrival(1);
		dispatcher.scheduleArrival(10);
		
		dispatcher.scheduleArrival(55);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		dispatcher.scheduleArrival(60);
		
//		dispatcher.scheduleArrival(70);
//		dispatcher.scheduleArrival(70);
//		dispatcher.scheduleArrival(70);
//		dispatcher.scheduleArrival(70);

		dispatcher.run();
	}
}
