package edu.giocc.util;

/**
 * Interface for any collection that can be implemented using integer indices
 * for random access such as array lists.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this collection.
 */
public interface Indexable<E> extends Collection<E> {
	/**
	 * Inserts a given element at the tail of this collection.
	 * 
	 * @param e the element to add.
	 */
	public abstract void add(E e);

	/**
	 * Inserts a given element at the specified index into this collection.
	 * 
	 * @param e the element to add.
	 * @param i the index of insertion.
	 */
	public abstract void insert(E e, int i);

	/**
	 * Retrieves and returns the element at a given index from this collection.
	 * 
	 * @param i the index of the element.
	 * @return the element at the given index.
	 */
	public abstract E get(int i);

	/**
	 * Updates an element at the given index with the specified element in this
	 * collection.
	 * 
	 * @param e the updated element.
	 * @param i the index of the element.
	 */
	public abstract void set(E e, int i);

	/**
	 * Removes an element at the given index from this collection..
	 * 
	 * @param i the index of the element to remove.
	 */
	public abstract void remove(int i);
}
