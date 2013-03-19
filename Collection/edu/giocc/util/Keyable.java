package edu.giocc.util;

public interface Keyable<K, V> extends Collection<K> {
	public void insert(K k, V v);
	public V get(K k);
	public void remove(K k);
}
