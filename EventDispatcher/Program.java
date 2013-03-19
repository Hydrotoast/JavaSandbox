import edu.giocc.util.router.EventDispatcher.Event;
import edu.giocc.util.router.EventDispatcher.EventDispatcher;
import edu.giocc.util.router.EventDispatcher.Handler;


public class Program {

	public static void main(String[] args) {
		EventDispatcher dispatcher = new EventDispatcher();
		dispatcher.registerChannel(Event.class, new Handler());
		dispatcher.dispatch(new Event());
	}

}
