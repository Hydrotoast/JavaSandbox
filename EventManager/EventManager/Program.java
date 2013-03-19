package EventManager;

public class Program {
	public static void main(String[] args) {
		EventManager dispatcher = new EventManager();
		dispatcher.registerObserver(ArrivalEvent.class, new ArrivalHandler());
		dispatcher.registerObserver(DepartureEvent.class, new DepartureHandler());
		
		dispatcher.scheduleArrival(5);
		dispatcher.scheduleArrival(1);
		dispatcher.scheduleArrival(10);
		
		dispatcher.scheduleArrival(55);
		dispatcher.scheduleArrival(60);

		dispatcher.run();
	}
}
