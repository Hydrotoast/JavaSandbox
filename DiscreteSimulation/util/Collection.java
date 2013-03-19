package util;

/**
 * Interface for containing objects.
 * 
 * @author gcielo
 * @param <E> the type of elements contained by this collection.
 */
public interface Collection<E> {
	/**
	 * Returns true if this collection is empty.
	 * 
	 * @return true if this collection is empty.
	 */
	public abstract boolean isEmpty();

	/**
	 * Returns the size of this collection.
	 * 
	 * @return the size of this collection.
	 */
	public abstract int size();

	/**
	 * Purges all items from this collection.
	 */
	public abstract void clear();
}
