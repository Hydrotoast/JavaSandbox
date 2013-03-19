package edu.giocc.util;

/**
 * Stacks order elements as a LIFO: all elements are inserted and removed from
 * the head of the list.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this collection.
 */
public interface Stack<E> {
	/**
	 * Pushes a specified element to the head of the stack.
	 * 
	 * @param e the element to push.
	 */
	public void push(E e);

	/**
	 * Returns the element at the head of the stack.
	 * 
	 * @return the element at the head of the stack.
	 */
	public E peek();

	/**
	 * Removes the element at the head of the stack.
	 */
	public void pop();
}
