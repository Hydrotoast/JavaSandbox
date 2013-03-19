package edu.giocc.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a doubly-linked list with an iterator. The list can either
 * be used as a Stack or Queue.
 * 
 * @author gcielo
 * 
 * @param <E> the type of element contained by this collection.
 */
public class DoublyLinkedList<E> implements Collection<E>, Iterable<E> {
	/**
	 * An entry within this doubly-linked list that encapsulates the data and
	 * contains pointers to its neighboring entries.
	 * 
	 * @author gcielo
	 * 
	 * @param <E> the type of the element contained by this entry.
	 */
	private static class DoublyLinkedListEntry<E> {
		public E data;
		public DoublyLinkedListEntry<E> next;
		public DoublyLinkedListEntry<E> prev;

		public DoublyLinkedListEntry(E data, DoublyLinkedListEntry<E> next,
				DoublyLinkedListEntry<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * Implementation of the Iterator interface that can traverse through this
	 * doubly-linked list sequentially.
	 * 
	 * @author gcielo
	 * 
	 * @param <T> the type of element iterated.
	 */
	public class DoublyLinkedListIterator<T> implements Iterator<T> {
		private DoublyLinkedListEntry<T> cursor;
		private boolean removeFlag;

		public DoublyLinkedListIterator(DoublyLinkedListEntry<T> head) {
			this.cursor = head;
		}

		@Override
		public boolean hasNext() {
			return (cursor == head) ? cursor.next != tail : cursor != tail;
		}

		@Override
		public T next() {
			removeFlag = false;
			cursor = cursor.next;
			return cursor.data;
		}

		@Override
		public void remove() {
			if (removeFlag)
				throw new IllegalStateException();
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			removeFlag = true;
			count--;
		}
	}

	/**
	 * First entry in this list as a sentinel.
	 */
	private transient DoublyLinkedListEntry<E> head;

	/**
	 * Last entry in this list as a sentinel.
	 */
	private transient DoublyLinkedListEntry<E> tail;

	/**
	 * The number of elements in this list.
	 */
	private transient int count;

	/**
	 * Initializes the head and tail sentinels and initializes the count as
	 * empty.
	 */
	public DoublyLinkedList() {
		head = new DoublyLinkedListEntry<E>(null, tail, null);
		tail = new DoublyLinkedListEntry<E>(null, null, head);
		head.next = tail;
		count = 0;
	}

	/**
	 * Adds an element e to the front of this list.
	 * 
	 * @param e the element to add.
	 */
	public void addFirst(E e) {
		head.next = new DoublyLinkedListEntry<E>(e, head.next, head);
		head.next.next.prev = head.next;
		count++;
	}

	/**
	 * Adds an element e to the end of this list.
	 * 
	 * @param e the element to add.
	 */
	public void addLast(E e) {
		tail.prev = new DoublyLinkedListEntry<E>(e, tail, tail.prev);
		tail.prev.prev.next = tail.prev;
		count++;
	}

	/**
	 * Retrieves the first element of this list.
	 * 
	 * @return the first element of this list.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public E getFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return head.next.data;
	}

	/**
	 * Retrieves the last element of this list.
	 * 
	 * @return the last element of this list.
	 * @throws NoSuchElementException if this list is empty.
	 */
	public E getLast() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return tail.prev.data;
	}

	/**
	 * Retrieves an element at a given index from this list.
	 * 
	 * @param index the element's position from the beginning.
	 * @return an element at the given index from this list.
	 * @throws NoSuchElementException if the list is empty.
	 */
	public E get(int index) throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		DoublyLinkedListEntry<E> entry = head.next;
		for (int i = 0; i < index; i++)
			entry = entry.next;

		return entry.data;
	}

	/**
	 * Removes the first element of this list.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 */
	public void removeFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		if (head.next.next == tail)
			tail.prev = head;
		head.next = head.next.next;
		count--;
	}

	/**
	 * Removes the last element of this list.
	 * 
	 * @throws NoSuchElementException if the list is empty.
	 */
	public void removeLast() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		if (tail.prev.prev == head)
			head.next = tail;
		tail.prev = tail.prev.prev;
		count--;
	}

	/**
	 * Removes an element e from this list.
	 * 
	 * @param e the element to remove.
	 * @throws NoSuchElementException if the element does not exist.
	 */
	public void remove(E e) throws NoSuchElementException {
		for (DoublyLinkedListEntry<E> entry = head.next; entry != tail; entry = entry.next) {
			if (entry.data.equals(e)) {
				entry.prev.next = entry.next;
				entry.next.prev = entry.prev;
				count--;
				return;
			}
		}

		throw new NoSuchElementException();
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
	public void clear() {
		head.next = tail;
		count = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(head);
	}
}
