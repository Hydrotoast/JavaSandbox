package edu.giocc.util.SkipList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.giocc.util.Map;

/**
 * Skip List Map associates keys and values stored as a paired entry within a
 * Skip List data structure.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <K> the type of the keys contained by this map.
 * @param <V> the type of the values contained by this map.
 */
public class SkipListMap<K extends Comparable<K>, V> implements Map<K, V> {
	/**
	 * Skip List Map Entry associates a single key with a single value for
	 * storage.
	 * 
	 * @author Gio Carlo Cielo
	 * 
	 * @param <K> the type of the keys contained by this entry.
	 * @param <V> the type of the values contained by this entry.
	 */
	private static class SkipListMapEntry<K extends Comparable<K>, V>
			implements Comparable<SkipListMapEntry<K, V>> {
		public K key;
		public V value;

		public SkipListMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo(SkipListMapEntry<K, V> o) {
			return this.key.compareTo(o.key);
		}
		
		@Override
		public boolean equals(Object o) {
			if (!o.getClass().isAssignableFrom(SkipListMapEntry.class))
				return false;

			return ((SkipListMapEntry<?, ?>) o).key.equals(key);
		}
	}

	/**
	 * The internal storage data structure that stores all elements of this map.
	 */
	private SkipList<SkipListMapEntry<K, V>> list;

	/**
	 * Initializes this map with default values.
	 */
	public SkipListMap() {
		list = new SkipList<SkipListMapEntry<K, V>>(
				new Comparator<SkipListMapEntry<K, V>>() {

					@Override
					public int compare(SkipListMapEntry<K, V> op1,
							SkipListMapEntry<K, V> op2) {
						if (op1 == null)
							return -1;
						else if (op2 == null)
							return 1;
						else
							return op1.compareTo(op2);
					}

				});
	}

	@Override
	public void insert(K k, V v) {
		list.insert(new SkipListMapEntry<K, V>(k, v));
	}

	@Override
	public V get(K k) {
		return list.get(new SkipListMapEntry<K, V>(k, null)).value;
	}

	@Override
	public void remove(K k) {
		list.remove(new SkipListMapEntry<K, V>(k, null));
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean containsKey(K k) {
		try {
			get(k);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public boolean containsValue(V v) {
		return false;
	}

	public Iterator<SkipListMapEntry<K, V>> iterator() {
		return list.iterator();
	}
}
