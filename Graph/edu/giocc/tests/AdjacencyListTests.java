package edu.giocc.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.Graph.AdjacencyList;

public class AdjacencyListTests {
	private AdjacencyList<Integer> graph;

	@Before
	public void setUp() throws Exception {
		graph = new AdjacencyList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		graph.clear();
	}

	@Test
	public void canBuild123() {
		build123();
		assertTrue(graph.contains(11));
		assertTrue(graph.contains(22));
		assertTrue(graph.contains(33));
		
		assertEquals("(11 11) (22 22) (33 33)", graph.printParentheses());
	}

	@Test
	public void canBuildLine() {
		build123();
		graph.insertDirectedEdge(11, 22);
		graph.insertDirectedEdge(22, 33);

		assertEquals(22, graph.adjacent(11).get(0).data.intValue());
		assertEquals(33, graph.adjacent(22).get(0).data.intValue());

		assertEquals("(11 (22 (33 33) 22) 11)", graph.printParentheses());
	}
	
	@Test
	public void canCalculateShortestPath() {
		graph.insertVertex(11);
		graph.insertVertex(22);
		graph.insertVertex(33);
		graph.insertVertex(44);
		graph.insertVertex(55);

		graph.insertDirectedEdge(11, 22);
		graph.insertDirectedEdge(11, 33);
		graph.insertDirectedEdge(22, 33);
		graph.insertDirectedEdge(22, 44);
		graph.insertDirectedEdge(33, 55);
		graph.insertDirectedEdge(44, 55);

		assertEquals(2, graph.adjacent(11).size());
		assertEquals(2, graph.shortestPath(11, 44));
	}
	
	private void build123() {
		graph.insertVertex(11);
		graph.insertVertex(22);
		graph.insertVertex(33);
	}
}
