package edu.giocc.util;

import java.util.NoSuchElementException;

/**
 * Doubly-linked list implementation of the queue interface that does not have
 * capacity restrictions.
 * 
 * @author gcielo
 * 
 * @param <E> the type of elements contained by this queue.
 */
public class QueueList<E> implements Queue<E> {
	/**
	 * The linked-list data structure that contains all elements of this queue.
	 */
	private DoublyLinkedList<E> list;

	public QueueList() {
		list = new DoublyLinkedList<E>();
	}

	/**
	 * Returns true if this queue is empty.
	 * 
	 * @return true if this queue is empty.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the size of this queue.
	 * 
	 * @return the size of this queue.
	 */
	public int size() {
		return list.size();
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

	@Override
	public E peek() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return list.getFirst();
	}

	@Override
	public E poll() {
		if (list.isEmpty())
			return null;

		E data = list.getFirst();
		list.removeFirst();
		return data;
	}

	@Override
	public void dequeue() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		list.removeFirst();
	}
}
