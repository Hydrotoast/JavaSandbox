package router;

/**
 * Channels push messages downstream a data flow.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of messages pushed by this channel.
 */
public interface Channel<E extends Content> {
	public void dispatch(E content);
}
