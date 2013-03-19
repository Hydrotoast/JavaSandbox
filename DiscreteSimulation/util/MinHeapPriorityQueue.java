package util;
import java.util.Comparator;

import lang.EmptyHeapException;


public class MinHeapPriorityQueue<E> implements PriorityQueue<E> {
	private MinHeap<E> heap;
	
	public MinHeapPriorityQueue(Comparator<E> comparator) {
		heap = new MinHeap<E>(comparator);
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
	public E peek() throws EmptyHeapException {
		return heap.min();
	}

	@Override
	public void dequeue() throws EmptyHeapException {
		heap.removeMin();
	}

	@Override
	public void enqueue(E e) {
		heap.insert(e);
	}

}
