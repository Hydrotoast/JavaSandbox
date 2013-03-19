package edu.giocc.util.queue;

import java.util.Comparator;

import edu.giocc.util.heap.MaxHeap;

/**
 * Priority queue implemented with a maximum heap data structure for logarithmic
 * time insertions and removals.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <E> the type of elements contained by this queue.
 */
public class MaxHeapPriorityQueue<E> implements PriorityQueue<E> {
	/**
	 * The heap that stores the elements of this queue.
	 */
	private MaxHeap<E> heap;

	/**
	 * Initializes the storage for this queue.
	 * 
	 * @param comparator implements comparison algorithm for sorting keys.
	 */
	public MaxHeapPriorityQueue(Comparator<E> comparator) {
		heap = new MaxHeap<E>(comparator);
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public void enqueue(E e) {
		heap.add(e);
	}

	@Override
	public E peek() {
		return heap.peek();
	}

	@Override
	public E poll() {
		if (heap.isEmpty())
			return null;

		E data = heap.peek();
		heap.remove();
		return data;
	}

	@Override
	public void dequeue() {
		heap.remove();
	}

	@Override
	public void clear() {
		heap.clear();
	}

}
