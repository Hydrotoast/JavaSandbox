package util;

import lang.EmptyQueueException;


public class ListQueue<E> implements Queue<E> {
	private static class QueueEntry<E> {
		public E data;
		public QueueEntry<E> next;
		public QueueEntry<E> prev;
		
		public QueueEntry(E data, QueueEntry<E> next, QueueEntry<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/* Sentinels */
	private QueueEntry<E> head;
	private QueueEntry<E> tail;
	
	private int count;
	
	public ListQueue() {
		head = new QueueEntry<E>(null, tail, null);
		tail = new QueueEntry<E>(null, null, head);
		count = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public void enqueue(E e) {
		insertToLast(e);
		count++;
	}

	@Override
	public E peek() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return head.next.data;
	}

	@Override
	public void dequeue() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		removeFromFront();
		count--;
	}

	private void insertToLast(E e) {
		QueueEntry<E> entry = new QueueEntry<E>(e, tail, tail.prev);
		tail.prev.next = entry;
		tail.prev = entry;
	}

	private void removeFromFront() {
		head = head.next;
	}
}
