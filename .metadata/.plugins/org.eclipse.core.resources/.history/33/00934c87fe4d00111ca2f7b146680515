package devs;

import java.util.Comparator;

public class DefaultComparator<E> implements Comparator<E> {
	@Override
	public int compare(E op1, E op2) throws ClassCastException {
		return ((Comparable<E>)op1).compareTo(op2);
	}
}
