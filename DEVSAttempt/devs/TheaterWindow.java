package devs;

public class TheaterWindow implements Observer {
	private CustomerLine line;
	
	public TheaterWindow(CustomerLine s) {
		line = s;
	}
	
	@Override
	public void update(Observable s) {
		if (s.equals(line)) {
			// Entity e = s.dequeue();
			// e.nextB();
			// set customer entity state to its next state
		}
	}
}