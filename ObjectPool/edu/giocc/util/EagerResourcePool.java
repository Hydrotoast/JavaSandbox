package edu.giocc.util;
import edu.giocc.lang.EmptyPoolException;


public interface EagerResourcePool<T> {
	public abstract T acquireResource() throws EmptyPoolException;
	public abstract void releaseResource(T resource);
}
