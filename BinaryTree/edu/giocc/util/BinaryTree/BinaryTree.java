package edu.giocc.util.BinaryTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTree<T> {
	protected static class BinaryTreeNode<T> {
		public T data;
		public BinaryTreeNode<T> left;
		public BinaryTreeNode<T> right;

		public BinaryTreeNode(T data, BinaryTreeNode<T> left,
				BinaryTreeNode<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	protected Comparator<T> comparator;
	public BinaryTreeNode<T> root;

	public BinaryTree(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T lookup(BinaryTreeNode<T> cursor, T data) {
		if (cursor == null)
			throw new NoSuchElementException();
		else if (cursor.data.equals(data))
			return cursor.data;
		else {
			if (comparator.compare(data, cursor.data) < 0)
				return lookup(cursor.left, data);
			else
				return lookup(cursor.right, data);
		}
	}
	
	public T bfs(T target) {
		return bfs(root, target).data;
	}
	
	public boolean dfs(T target) {
		return dfs(root, target);
	}

	public void add(T data) {
		if (root == null) {
			root = new BinaryTreeNode<T>(data, null, null);
			return;
		}

		root = insert(root, data);
	}

	public int size() {
		return size(root);
	}

	public int maxDepth() {
		return maxDepth(root);
	}

	public T minValue() {
		return minValue(root);
	}

	public boolean hasPathSum(String target) {
		return hasPathSum(root, "", target);
	}

	public void mirror() {
		mirror(root);
	}

	public void doubleTree() {
		doubleTree(root);
	}

	public String printTree(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return "";
		else {
			return printTree(cursor.left) + cursor.data
					+ printTree(cursor.right);
		}
	}

	public String printPostorder(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return "";
		else
			return printPostorder(cursor.left) + printPostorder(cursor.right)
					+ cursor.data;
	}

	protected BinaryTreeNode<T> insert(BinaryTreeNode<T> cursor, T data) {
		if (cursor == null) {
			return new BinaryTreeNode<T>(data, null, null);
		} else {
			if (comparator.compare(data, cursor.data) < 0)
				cursor.left = insert(cursor.left, data);
			else
				cursor.right = insert(cursor.right, data);

			return cursor;
		}
	}

	protected int size(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return 0;
		else
			return size(cursor.left) + 1 + size(cursor.right);
	}

	protected BinaryTreeNode<T> bfs(BinaryTreeNode<T> cursor, T target) {
		Queue<BinaryTreeNode<T>> pendingQueue = new LinkedList<BinaryTreeNode<T>>();
		pendingQueue.add(cursor);

		while (!pendingQueue.isEmpty()) {
			cursor = pendingQueue.remove();

			if (cursor.data.equals(target))
				return cursor;

			if (cursor.left != null)
				pendingQueue.add(cursor.left);

			if (cursor.right != null)
				pendingQueue.add(cursor.right);
		}

		throw new NoSuchElementException();
	}

	protected boolean dfs(BinaryTreeNode<T> cursor, T target) {
		if (cursor == null)
			return false;
		else if (cursor.data.equals(target))
			return true;

		return dfs(cursor.left, target) || dfs(cursor.right, target);
	}

	protected int maxDepth(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return 0;
		else
			return 1 + Math.max(maxDepth(cursor.left), maxDepth(cursor.right));
	}

	protected T minValue(BinaryTreeNode<T> cursor) {
		if (cursor.left == null)
			return cursor.data;
		else
			return minValue(cursor.left);
	}

	protected BinaryTreeNode<T> mirror(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return null;

		BinaryTreeNode<T> swap = cursor.left;
		cursor.left = mirror(cursor.right);
		cursor.right = mirror(swap);

		return cursor;
	}

	protected boolean hasPathSum(BinaryTreeNode<T> cursor, String acc,
			String target) {
		if (cursor == null)
			return false;

		acc = acc.toString() + cursor.data.toString();
		if (acc.equals(target))
			return true;
		else
			return hasPathSum(cursor.left, acc, target)
					|| hasPathSum(cursor.right, acc, target);
	}

	protected BinaryTreeNode<T> doubleTree(BinaryTreeNode<T> cursor) {
		if (cursor == null)
			return null;

		BinaryTreeNode<T> node = new BinaryTreeNode<T>(cursor.data,
				doubleTree(cursor.left), null);
		cursor.left = node;
		cursor.right = doubleTree(cursor.right);

		return cursor;
	}

	public static boolean sameTree(BinaryTreeNode<?> op1, BinaryTreeNode<?> op2) {
		if (op1 == null && op2 == null)
			return true;
		else if (!op1.data.equals(op2.data))
			return false;
		else
			return sameTree(op1.left, op2.left)
					&& sameTree(op1.right, op2.right);
	}
}
