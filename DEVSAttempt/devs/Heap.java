package devs;

import java.util.ArrayList;
import java.util.Iterator;


public class Heap<K, V> implements PriorityQueue<K, V> {
	private static class Entry<K, V> {
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}
	
	private static final int ROOT_NODE = 1;
	
	private ArrayList<Entry<K, V>> heap;
	private int size;

	public Heap() {
		heap = new ArrayList<Entry>();
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Comparable<E> min() {
		return heap.get(ROOT_NODE);
	}

	@Override
	public void insert(Comparable<E> entry) {		
		for (int i = 1; i < heap.size(); i++)
			if (heap.get(i).compareTo((E) entry) > 0)
				heap.add(2 * i, entry);
				
		
	}

	@Override
	public void removeMin() {
		
	}

}
