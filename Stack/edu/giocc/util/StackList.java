package edu.giocc.util;

/**
 * List Stack is a linked-list implementation of the Stack ADT.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this collection.
 */
public class StackList<E> implements Stack<E> {
	/**
	 * List that stores the elements of this stack.
	 */
	private SinglyLinkedList<E> list;

	/**
	 * Initializes the storage list for this stack.
	 */
	public StackList() {
		list = new SinglyLinkedList<E>();
	}
	
	/**
	 * Returns true if this stack is empty.
	 * 
	 * @return true if this stack is empty.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the size of this stack.
	 * 
	 * @return the size of this stack.
	 */
	public int size() {
		return list.size();
	}

	@Override
	public void push(E e) {
		list.addFirst(e);
	}

	@Override
	public E peek() {
		return list.getFirst();
	}

	@Override
	public void pop() {
		list.removeFirst();
	}

}
