package events;
import router.Channel;

public interface Handler extends Channel<TimeEvent> {
	public void dispatch(TimeEvent message);
}
