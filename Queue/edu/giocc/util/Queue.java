package edu.giocc.util;

import java.util.NoSuchElementException;

/**
 * Queues typically order elements as a FIFO; however, this is not necessarily
 * true for priority queues. Regardless of the ordering, all new elements are
 * inserted at the tail of the queue and all elements are removed from the head
 * of the queue.
 * 
 * @author gcielo
 * 
 * @param <E> the type of elements contained by this collection.
 */
public interface Queue<E> {
	/**
	 * Inserts a specified element to the tail of this queue.
	 * 
	 * @param e the element to add.
	 */
	public void enqueue(E e);

	/**
	 * Retrieves the element at the head of this queue.
	 * 
	 * @return the element at the head of this queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Retrieves and removes the element at the head of this queue or returns
	 * <tt>null</tt> if this queue is empty.
	 * 
	 * @return the element at the head of this queue or <tt>null</tt> if this
	 *         queue is empty.
	 */
	public E poll();

	/**
	 * Removes the element at the head of this queue.
	 * 
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public void dequeue() throws NoSuchElementException;
}
