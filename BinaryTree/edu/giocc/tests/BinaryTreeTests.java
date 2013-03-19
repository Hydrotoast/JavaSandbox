package edu.giocc.tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.BinaryTree.BinaryTree;

public class BinaryTreeTests {
	private BinaryTree<Integer> tree;

	@Before
	public void setUp() throws Exception {
		tree = new BinaryTree<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}

		});
	}

	@Test
	public void canBuild123() {
		populate123();
		assertEquals("112233", tree.printTree(tree.root));
		assertEquals("113322", tree.printPostorder(tree.root));
	}

	@Test
	public void canGetSize() {
		populate123();
		assertEquals(3, tree.size());
	}

	@Test
	public void canGetMaxDepth() {
		assertEquals(0, tree.maxDepth());

		populate123();
		assertEquals(2, tree.maxDepth());
	}

	@Test
	public void canGetMinValue() {
		populate123();
		assertEquals(11, tree.minValue().intValue());
	}

	@Test
	public void canMirror() {
		populate123();
		tree.mirror();
		assertEquals("332211", tree.printTree(tree.root));
	}

	@Test
	public void canContainPathSums() {
		populate123();
		assertTrue(tree.hasPathSum("2211"));
		assertTrue(tree.hasPathSum("2233"));
	}
	
	@Test
	public void canDoubleTree() {
		populate123();
		tree.doubleTree();
		assertEquals("111122223333", tree.printTree(tree.root));
	}
	
	@Test
	public void canCheckSameTree() {
		populate123();
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
			
		});
		tree2.add(22);
		tree2.add(11);
		tree2.add(33);
		
		assertTrue(BinaryTree.sameTree(tree.root, tree2.root));
	}
	
	@Test
	public void canBFS() {
		populate123();
		assertEquals(22, tree.bfs(22).intValue());
		assertEquals(11, tree.bfs(11).intValue());
		assertEquals(33, tree.bfs(33).intValue());
	}
	
	@Test
	public void canDFS() {
		populate123();
		assertTrue(tree.dfs(22));
		assertTrue(tree.dfs(11));
		assertTrue(tree.dfs(33));
	}

	private void populate123() {
		tree.add(22);
		tree.add(11);
		tree.add(33);
	}
}
