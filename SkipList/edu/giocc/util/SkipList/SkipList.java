package edu.giocc.util.SkipList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import edu.giocc.util.Collection;

/**
 * Skip List is a probabilistic data structure that act as an alternative for
 * balanced search trees. Effectively, insertion, removal and retrieval have a
 * worst-case, logarithmic cost.
 * 
 * @author Gio Carlo Cielo
 * 
 * @param <T> the type of the datas contained by this list.
 * @param <V> the type of the values contained by this list.
 */
public class SkipList<T> implements Collection<T>, Iterable<T> {
	/**
	 * Skip List Node contains the data necessary for skip list interactions
	 * including the data value and all forward pointers.
	 * 
	 * @author Gio Carlo Cielo
	 */
	private class SkipListNode {
		public T data;
		public ArrayList<SkipListNode> forward;

		public SkipListNode(T data) {
			this.data = data;
			forward = new ArrayList<SkipListNode>();
			forward.ensureCapacity(maxRank);
		}

		@Override
		public String toString() {
			if (data == null)
				return "{[]}";

			return "{" + this.data.toString() + "}";
		}
	}

	/**
	 * Iterator for all skip list nodes.
	 * 
	 * @author Gio Carlo Cielo
	 */
	public class SkipListIterator implements Iterator<T> {
		private SkipListNode cursor;

		public SkipListIterator() {
			cursor = header;
		}

		@Override
		public boolean hasNext() {
			return cursor.forward.get(0) != null;
		}

		@Override
		public T next() {
			T data = cursor.data;
			cursor = cursor.forward.get(0);
			return data;
		}

		@Override
		public void remove() {
			SkipList.this.remove(cursor.data);
		}
	}

	/**
	 * Totally orders all nodes in this list.
	 */
	private Comparator<T> comparator;

	/**
	 * Generates random numbers for the probability distribution.
	 */
	private Random randomGen;

	/**
	 * Maintains the probabilistic ratio of the list structure.
	 */
	private int probDistro;

	/**
	 * Defines the head of all lists.
	 */
	private SkipListNode header;

	/**
	 * Maintains the current, maximum rank of this list.
	 */
	private int maxRank;

	/**
	 * Maintains the number of elements contained by this list.
	 */
	private int count;

	/**
	 * Constructor with only a comparator.
	 * 
	 * @param comparator the comparator for comparisons.
	 */
	public SkipList(Comparator<T> comparator) {
		this.comparator = comparator;
		this.randomGen = new Random(System.currentTimeMillis());
		probDistro = 4;

		header = new SkipListNode(null);

		count = 0;
		maxRank = 1;
	}

	/**
	 * Constructor with a comparator and probability distribution.
	 * 
	 * @param comparator the comparator for comparisons.
	 * @param probDistro the divisor for the probability distribution fraction.
	 */
	public SkipList(Comparator<T> comparator, Random randomGen, int probDistro) {
		this.comparator = comparator;
		this.randomGen = randomGen;
		this.probDistro = probDistro;

		header = new SkipListNode(null);

		count = 0;
		maxRank = 1;
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public void clear() {
		header.forward = new ArrayList<SkipListNode>();

		maxRank = 1;
	}

	/**
	 * Inserts a new node with the specified data and value into the skip list.
	 * 
	 * @param data the data to add.
	 * @param value the value to add.
	 */
	public void insert(T data) {
		ArrayList<SkipListNode> updates = buildUpdates(data);
		int newRank = randRank();

		// Invariant: maxRank > all ranks
		if (newRank > maxRank) {
			// Invariant: updates contains maxRank -1 nodes to update
			for (int i = maxRank; i < newRank; i++)
				updates.add(i, header);

			increaseRank(newRank);
		}

		SkipListNode node = new SkipListNode(data);
		SkipListNode update;
		for (int rank = 0; rank < newRank; rank++) {
			update = updates.get(rank);

			// Invariant: forward rank >= current height
			if (update.forward.size() > rank)
				node.forward.add(update.forward.get(rank));

			update.forward.add(rank, node);
		}

		count++;
	}

	/**
	 * Returns a value with the specified data.
	 * 
	 * @param k the data associated with the value.
	 * @return the value associated with the data.
	 */
	public T get(T data) {
		if (isEmpty())
			throw new NoSuchElementException();

		SkipListNode prev = getPrevNode(data);
		if (!prev.forward.get(0).data.equals(data))
			throw new NoSuchElementException();
		
		return prev.forward.get(0).data;
	}

	/**
	 * Removes the node with the specified data from this list.
	 * 
	 * @param data the data for the node to remove.
	 */
	public void remove(T data) {
		if (isEmpty())
			throw new NoSuchElementException();

		ArrayList<SkipListNode> updates = buildUpdates(data);
		SkipListNode cursor = getPrevNode(data);

		if (comparator.compare(cursor.forward.get(0).data, data) != 0)
			throw new NoSuchElementException();

		for (int i = 0; i < maxRank; i++)
			// Invariant: forward size >= i
			if (updates.get(i).forward.size() > i
					&& updates.get(i).forward.get(i).forward.size() > i
					&& comparator.compare(updates.get(i).forward.get(i).data,
							data) == 0)
				updates.get(i).forward.set(i,
						updates.get(i).forward.get(i).forward.get(i));

		count--;
	}

	/**
	 * Returns the node before the node with the specified data.
	 * 
	 * @param data the data of the node ahead.
	 * @return the node before the node with the specified data.
	 */
	private SkipListNode getPrevNode(T data) {
		SkipListNode cursor = header;

		// Invariant: maxrank - 1 downto 0 contains nodes
		for (int rank = maxRank - 1; rank >= 0; rank--)
			cursor = searchFile(rank, data);

		return cursor;
	}

	/**
	 * Returns the node before the node with the specified data.
	 * 
	 * @param rank the rank of the files to search.
	 * @param data the data of the node ahead.
	 * @return the node before the node with the specified data.
	 */
	private SkipListNode searchFile(int rank, T data) {
		// Invariant: forward size >= i
		SkipListNode cursor = header;
		while (cursor.forward.size() > rank
				&& comparator.compare(cursor.forward.get(rank).data, data) < 0)
			cursor = cursor.forward.get(rank);

		return cursor;
	}

	/**
	 * Builds and returns an update vector for the specified data.
	 * 
	 * @param data the data of node to add.
	 * @return an update vector for the specified data.
	 */
	private ArrayList<SkipListNode> buildUpdates(T data) {
		ArrayList<SkipListNode> updates = new ArrayList<SkipListNode>();

		// Invariant: maxrank - 1 downto 0 contains nodes
		for (int i = 0; i < maxRank; i++)
			updates.add(null);

		for (int rank = maxRank - 1; rank >= 0; rank--)
			updates.set(rank, searchFile(rank, data));

		return updates;
	}

	/**
	 * Returns a random rank.
	 * 
	 * @return a random rank.
	 */
	private int randRank() {
		int rank = 1;
		while (randomGen.nextInt(probDistro) == 0)
			rank++;

		return rank;
	}

	/**
	 * Increases the maximum rank of this list.
	 * 
	 * @param newRank the new rank of the list.
	 * @param updates the update vector to update.
	 */
	private void increaseRank(int newRank) {
		this.maxRank = newRank;
	}

	@Override
	public Iterator<T> iterator() {
		return new SkipListIterator();
	}
}
