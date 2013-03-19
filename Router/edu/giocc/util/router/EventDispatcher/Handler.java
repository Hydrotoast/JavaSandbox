package edu.giocc.util.router.EventDispatcher;
import edu.giocc.util.router.Channel;

public class Handler implements Channel<Event> {
	@Override
	public void dispatch(Event message) {
		System.out.println(message.getClass());
	}
}
