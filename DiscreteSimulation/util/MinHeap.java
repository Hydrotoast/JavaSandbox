package util;
import java.util.ArrayList;
import java.util.Comparator;

import lang.EmptyHeapException;

public class MinHeap<E> {
	private ArrayList<E> entries;
	private Comparator<E> comparator;

	public MinHeap(Comparator<E> comparator) {
		entries = new ArrayList<E>();
		entries.add(0, null);
		this.comparator = comparator;
	}

	public boolean isEmpty() {
		return entries.size() == 1;
	}

	public int size() {
		return entries.size() - 1;
	}

	public void insert(E e) {
		entries.add(e);
		bubbleUp(entries.size() - 1);
	}

	public E min() throws EmptyHeapException {
		if (entries.size() == 1)
			throw new EmptyHeapException();
		return entries.get(1);
	}

	public void removeMin() throws EmptyHeapException {
		if (entries.size() == 1)
			throw new EmptyHeapException();
		else if (entries.size() == 2)
			entries.remove(1);
		else {
			entries.set(1, entries.get(entries.size() - 1));
			entries.remove(entries.size() - 1);
			bubbleDown(1);
		}
	}

	private void swapEntries(int i, int j) {
		E swap = entries.get(i);
		entries.set(i, entries.get(j));
		entries.set(j, swap);
	}

	private void bubbleUp(int i) {
		int parent = (int) Math.floor(i >> 1);
		while (i > 1
				&& comparator.compare(entries.get(i), entries.get(parent)) < 0) {
			swapEntries(i, parent);
			i = parent;
		}
	}

	private void bubbleDown(int i) {
		int left = i << 1;
		int right = (i << 1) + 1;
		int smallest = i;

		if (left < entries.size()
				&& comparator.compare(entries.get(left), entries.get(smallest)) < 0)
			smallest = left;
		if (right < entries.size()
				&& comparator.compare(entries.get(right), entries.get(smallest)) < 0)
			smallest = right;

		if (smallest != i) {
			swapEntries(i, smallest);
			bubbleDown(smallest);
		}
	}
}