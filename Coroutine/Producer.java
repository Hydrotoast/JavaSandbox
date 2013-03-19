
public interface Producer<T> {
	public boolean isDone();
	public abstract T produce();
}
