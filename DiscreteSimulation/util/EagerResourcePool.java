package util;

import lang.EmptyPoolException;


public interface EagerResourcePool<T> {
	public abstract T acquireResource() throws EmptyPoolException;
	public abstract void releaseResource(T resource);
}
