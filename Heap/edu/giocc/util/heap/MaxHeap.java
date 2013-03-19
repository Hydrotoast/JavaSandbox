package edu.giocc.util.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Heap implementation with the highest key at the root of the tree.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this heap.
 */
public class MaxHeap<E> implements Heap<E> {
	/**
	 * The list that stores the elements of this heap.
	 */
	private ArrayList<E> entries;

	/**
	 * Compares keys for sorting.
	 */
	private Comparator<E> comparator;

	/**
	 * Initializes the storage for this heap.
	 * 
	 * @param comparator the comparison algorithm for sorting keys.
	 */
	public MaxHeap(Comparator<E> comparator) {
		entries = new ArrayList<E>();
		entries.add(0, null);
		this.comparator = comparator;
	}

	@Override
	public boolean isEmpty() {
		return entries.size() == 1;
	}

	@Override
	public int size() {
		return entries.size() - 1;
	}

	@Override
	public void clear() {
		entries.removeAll(entries);
	}

	@Override
	public void add(E e) {
		entries.add(e);
		bubbleUp(entries.size() - 1);
	}

	@Override
	public E peek() {
		if (entries.size() == 1)
			throw new NoSuchElementException();
		return entries.get(1);
	}

	@Override
	public E poll() {
		if (entries.isEmpty())
			return null;

		E data = peek();
		remove();
		return data;
	}

	@Override
	public void remove() {
		if (entries.size() == 1)
			throw new NoSuchElementException();
		else if (entries.size() == 2)
			entries.remove(1);
		else {
			entries.set(1, entries.get(entries.size() - 1));
			entries.remove(entries.size() - 1);
			bubbleDown(1);
		}
	}

	/**
	 * Swaps the position of two entries within the heap.
	 * 
	 * @param i the first position to swap.
	 * @param j the second position to swap.
	 */
	private void swapEntries(int i, int j) {
		E swap = entries.get(i);
		entries.set(i, entries.get(j));
		entries.set(j, swap);
	}

	/**
	 * Pushes an entry towards the root.
	 * 
	 * @param i the index of the entry.
	 */
	private void bubbleUp(int i) {
		int parent = (int) Math.floor(i / 2);
		while (i > 1
				&& comparator.compare(entries.get(i), entries.get(parent)) > 0) {
			swapEntries(i, parent);
			i = parent;
		}
	}

	/**
	 * Pushes an entry away from the root.
	 * 
	 * @param i the index of the entry.
	 */
	private void bubbleDown(int i) {
		int left = i << 1;
		int right = (i << 1) + 1;
		int largest = i;

		if (left < entries.size()
				&& comparator.compare(entries.get(left), entries.get(largest)) > 0)
			largest = left;
		if (right < entries.size()
				&& comparator.compare(entries.get(right), entries.get(largest)) > 0)
			largest = right;

		if (largest != i) {
			swapEntries(i, largest);
			bubbleDown(largest);
		}
	}
}