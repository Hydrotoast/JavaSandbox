package edu.giocc.util.router.ContentBasedRouter;

import java.util.Comparator;

import edu.giocc.util.queue.MaxHeapPriorityQueue;
import edu.giocc.util.router.Message;
import edu.giocc.util.router.QueueChannel;
import edu.giocc.util.router.Router;

/**
 * Implements a router for events. This router uses an internal priority queue
 * for determining the appropriate channel for the next dispatch.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of events routed by this router.
 */
public class ContentBasedRouter implements Router<Message> {
	/**
	 * The route dictionary which maintains the least congested route on top.
	 */
	private MaxHeapPriorityQueue<QueueChannel<Message>> routes;

	/**
	 * Initializes the event dispatcher and its route handling mechanism.
	 */
	public ContentBasedRouter() {
		routes = new MaxHeapPriorityQueue<QueueChannel<Message>>(
				new Comparator<QueueChannel<Message>>() {

					@Override
					public int compare(QueueChannel<Message> arg0,
							QueueChannel<Message> arg1) {
						if (arg0.getCongestion() > arg1.getCongestion())
							return 1;
						else if (arg0.getCongestion() < arg1.getCongestion())
							return -1;
						else
							return 0;
					};
				});
	}

	@Override
	public void registerChannel(QueueChannel<? extends Message> channel) {
		routes.enqueue((ContentChannel) channel);
	}

	@Override
	public void dispatch(Message content) {
		routes.peek().dispatch(content);
		routes.enqueue(routes.poll());
	}
}
