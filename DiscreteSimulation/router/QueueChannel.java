package router;

/**
 * Channels as pending queues for processes.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of messages contained by this channel.
 */
public interface QueueChannel<E extends Content> extends Comparable<QueueChannel<? extends Content>> {
	/**
	 * Retrieves the level of congestion in this channel.
	 * 
	 * @return the level of congestion in this channel.
	 */
	public int getCongestion();

	/**
	 * Dispatches the specified message to the appropriate recipient.
	 * 
	 * @param content the message to dispatch.
	 */
	public abstract void dispatch(E content);
}
