package edu.giocc.util.router.EventDispatcher;
import edu.giocc.util.router.Message;


public class Event implements Message {

	@Override
	public Class<? extends Message> getType() {
		return getClass();
	}

}
