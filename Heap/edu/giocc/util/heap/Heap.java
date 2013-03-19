package edu.giocc.util.heap;

import edu.giocc.lang.EmptyHeapException;
import edu.giocc.util.Collection;

/**
 * Interface for either maximum heaps or minimum heaps. Heaps store elements
 * according to the heap property where element keys are compared for ordering.
 * 
 * @author gcielo
 * @param <E> the type of elements contained in this collection.
 */
public interface Heap<E> extends Collection<E> {
	/**
	 * Inserts a specified element into this heap.
	 * 
	 * @param e the element to add.
	 */
	public abstract void add(E e);

	/**
	 * Retrieves the root element of this heap.
	 * 
	 * @return the root element of this heap.
	 */
	public abstract E peek() throws EmptyHeapException;

	/**
	 * Retrieves and removes the root element of this heap or <tt>null</tt> if this heap
	 * is empty.
	 * 
	 * @return the root element of this heap or <tt>null</tt> if this heap is empty.
	 */
	public abstract E poll();

	/**
	 * Removes the root element of this heap.
	 * 
	 * @return the root element of this heap.
	 */
	public abstract void remove();
}
