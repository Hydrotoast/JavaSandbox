package edu.giocc.Graph;

import java.util.ArrayList;
import java.util.NoSuchElementException;

// 1:17
public class DisjointSet<T> {
	private static class Node<T> {
		public T data;
		public Node<T> parent;

		public Node(T data) {
			this.data = data;
			this.parent = this;
		}
	}

	public ArrayList<Node<T>> forest;

	public DisjointSet() {
		forest = new ArrayList<Node<T>>();
	}

	public void makeSet(ArrayList<T> data) {
		for (T datum : data)
			forest.add(new Node<T>(datum));
	}

	public void union(T op1, T op2) {
		Node<T> u = findParent(op1);
		Node<T> v = findParent(op2);

		v.parent = u;
	}
	
	public boolean isSameSet(T op1, T op2) {
		return findParent(op1).equals(findParent(op2));
	}
	
	public Node<T> findParent(T data) {
		Node<T> u = findNode(data);
		// Path compression technique
		while (u.parent != u.parent.parent)
			u.parent = u.parent.parent;
		return u.parent;
	}
	
	private Node<T> findNode(T data) {
		for (Node<T> node : forest)
			if (node.data.equals(data))
				return node;
		throw new NoSuchElementException();
	}
	// 1:28
}
