package edu.giocc.util;

/**
 * Interface for any collection that depends on the order of insertion and
 * removal.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E>
 */
public interface Orderable<E> extends Collection<E> {
	/**
	 * Inserts a given element into this collection.
	 * 
	 * @param e the element to add.
	 */
	public void insert(E e);

	/**
	 * Retrieves the element at the head of this collection.
	 * 
	 * @return the element at the head of this collection.
	 */
	public E peek();

	/**
	 * Retrieves and removes the element at the head of this collection or
	 * returns null if this collection is empty.
	 * 
	 * @return the element at the head of this collection or null if this collection
	 *         is empty.
	 */
	public E poll();

	/**
	 * Removes the element at the head of this collection.
	 * 
	 * @param e the element at the head of this list.
	 */
	public void remove();
}
