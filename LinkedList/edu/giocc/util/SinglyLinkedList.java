package edu.giocc.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked list implementation with a head and tail for creating Queues. The list
 * implements the Iterable<E> interface for providing and iterator.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this list.
 */
public class SinglyLinkedList<E> implements Collection<E>, Iterable<E> {
	/**
	 * An entry within this singly-linked list that encapsulates the data and
	 * contains pointers to its neighboring entries.
	 * 
	 * @author Gio Carlo Cielo
	 * 
	 * @param <E> the type of the element contained by this entry.
	 */
	private static class SinglyLinkedListEntry<E> {
		public E data;
		public SinglyLinkedListEntry<E> next;

		public SinglyLinkedListEntry(E data, SinglyLinkedListEntry<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	/**
	 * Implementation of the Iterator interface that can traverse through this
	 * singly-linked list sequentially.
	 * 
	 * @author Gio Carlo Cielo
	 * 
	 * @param <T> the type of element iterated.
	 */
	private class SinglyLinkedListIterator<T> implements Iterator<T> {
		private SinglyLinkedListEntry<T> cursor;
		private SinglyLinkedListEntry<T> prev;
		private boolean removeFlag;

		public SinglyLinkedListIterator(SinglyLinkedListEntry<T> head) {
			cursor = head;
			prev = head;
			removeFlag = false;
		}

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public T next() {
			removeFlag = removeFlag ? false : true;
			T data = cursor.data;
			cursor = cursor.next;
			prev = cursor;
			return data;
		}

		@Override
		public void remove() {
			if (removeFlag)
				throw new IllegalStateException();
			if (prev == cursor)
				removeFirst();
			prev.next = cursor.next;
			removeFlag = true;
		}
	}

	/**
	 * First element of this list.
	 */
	private SinglyLinkedListEntry<E> head;

	/**
	 * Last element of this list.
	 */
	private SinglyLinkedListEntry<E> tail;

	/**
	 * Number of elements in this collection.
	 */
	private int count;

	/**
	 * Initializes the head and tail pointers as null and the number of elements
	 * to zero.
	 */
	public SinglyLinkedList() {
		head = null;
		tail = head;
		count = 0;
	}

	/**
	 * Inserts an element at the head of this list.
	 * 
	 * @param e the element to add.
	 */
	public void addFirst(E e) {
		head = new SinglyLinkedListEntry<E>(e, head);
		if (isEmpty())
			tail = head;

		count++;
	}

	/**
	 * Inserts an element at the end of this list.
	 * 
	 * @param e the element to add.
	 */
	public void addLast(E e) {
		SinglyLinkedListEntry<E> entry = new SinglyLinkedListEntry<E>(e, null);
		if (isEmpty())
			head = (tail = entry);
		else
			tail = (tail.next = entry);

		count++;
	}

	/**
	 * Retrieves the first element of this list.
	 * 
	 * @return the first element of this list.
	 */
	public E getFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		return head.data;
	}

	/**
	 * Retrieves the last element of this list.
	 * 
	 * @return the last element of this list.
	 */
	public E getLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		return tail.data;
	}

	/**
	 * Removes the first element of this list and decrements the count.
	 */
	public void removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		if (tail == head)
			tail = head.next;
		head = head.next;
		count--;
	}

	/**
	 * Removes the last element of this list and decrements the count.
	 */
	public void removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		if (head == tail) {
			head = tail.next;
			tail = tail.next;
			count--;
			return;
		}
		SinglyLinkedListEntry<E> cursor = head;
		while (cursor.next != tail)
			cursor = cursor.next;
		tail = cursor;
		count--;
	}

	/**
	 * Removes an element from this list.
	 * 
	 * @param e the specified element to remove.
	 */
	public void remove(E e) throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		if (e.equals(head.data)) {
			removeFirst();
			return;
		}

		for (SinglyLinkedListEntry<E> cursor = head; cursor != tail; cursor = cursor.next) {
			if (cursor.next.data.equals(e)) {
				cursor.next = cursor.next.next;
				count--;
				return;
			}
		}

		throw new NoSuchElementException();
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator<E>(head);
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
		head = null;
		tail = null;
		count = 0;
	}
}
