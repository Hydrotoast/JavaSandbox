package util;

public interface ResourcePool<T> {
	public abstract T acquireResource();
	public abstract void releaseResource(T resource);
}
