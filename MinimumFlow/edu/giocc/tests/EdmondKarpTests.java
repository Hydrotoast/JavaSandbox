package edu.giocc.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.giocc.Graph.EdmondKarp;

public class EdmondKarpTests {
	private EdmondKarp edmondKarp;
	private int src;
	private int dst;
	
	@Before
	public void setUp() throws Exception {
		int numNodes = 4;
		int[][] residualCapacity = {
				{0, 100, 100, 0},
				{0, 0, 1, 100},
				{0, 1, 0, 100},
				{0, 0, 0, 0}};
		this.src = 0;
		this.dst = 3;
		this.edmondKarp = new EdmondKarp(residualCapacity, numNodes);
	}

	@Test
	public void test() {
		assertEquals(200, this.edmondKarp.maxFlow(this.src, this.dst));
	}

}
