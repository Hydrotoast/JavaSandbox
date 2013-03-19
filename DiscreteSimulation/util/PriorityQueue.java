package util;

public interface PriorityQueue<E> {
	public abstract boolean isEmpty();
	public abstract int size();
	public abstract E peek() throws Exception;
	public abstract void dequeue() throws Exception;
	public abstract void enqueue(E e);
}
