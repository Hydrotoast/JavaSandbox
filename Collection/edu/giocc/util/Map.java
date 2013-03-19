package edu.giocc.util;

public interface Map<K, V> extends Keyable<K, V> {
	public boolean containsKey(K k);
	public boolean containsValue(V v);
}
