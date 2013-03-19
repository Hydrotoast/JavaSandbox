package util;

public interface Queue<E> {
	public boolean isEmpty();
	public int size();
	public void enqueue(E e);
	public E peek();
	public void dequeue();
}
