package edu.giocc.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.giocc.Graph.DisjointSet;
import edu.giocc.Graph.WeightedGraph;

public class DisjointSetTests {
	private DisjointSet<Integer> forest;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		vertices.add(1);
		vertices.add(2);
		vertices.add(3);
		vertices.add(4);
		vertices.add(5);
		vertices.add(6);

		forest = new DisjointSet<Integer>();
		forest.makeSet(vertices);
	}

	@Test
	public void canInitialize() {
		assertTrue(forest.isSameSet(1, 1));
		assertTrue(forest.isSameSet(2, 2));
		assertTrue(forest.isSameSet(3, 3));
		assertTrue(forest.isSameSet(4, 4));
		assertTrue(forest.isSameSet(5, 5));
		assertTrue(forest.isSameSet(6, 6));
	}
	
	@Test
	public void canPartition() {
		forest.union(1, 2);
		forest.union(1, 3);
		assertTrue(forest.isSameSet(2, 3));
		assertTrue(forest.isSameSet(1, 2));
		assertTrue(forest.isSameSet(1, 3));
		
		forest.union(6, 4);
		forest.union(6, 5);
		assertTrue(forest.isSameSet(4, 5));
		assertTrue(forest.isSameSet(6, 4));
		assertTrue(forest.isSameSet(6, 5));
	}
	//11:42
	
	@Test
	public void canFindMST() {

	}
}
