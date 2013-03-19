package edu.giocc.util.router;

/**
 * Routes messages to its respective channel.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of messages dispatched by this router.
 */
public interface Router<E extends Message> {
	/**
	 * Inserts a channel into the recipient list.
	 * 
	 * @param route the route to add.
	 */
	public abstract void registerChannel(QueueChannel<? extends E> channel);

	/**
	 * Dispatches the specified message to the appropriate channel.
	 * 
	 * @param mesage the message to dispatch.
	 */
	public abstract void dispatch(E content);
}
