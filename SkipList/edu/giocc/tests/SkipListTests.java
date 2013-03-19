package edu.giocc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.SkipList.SkipList;

public class SkipListTests {
	private SkipList<String> list;
	
	@Before
	public void setUp() {
		list = new SkipList<String>(new Comparator<String>() {

			@Override
			public int compare(String op1, String op2) {
				if (op1 == null)
					return -1;
				else if (op2 == null)
					return 1;
				else
					return op1.compareTo(op2);
			}
			
		});
	}
	
	@After
	public void tearDown() {
		list.clear();
	}
	
	@Test
	public void canInitialize() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void canUseHead() {
		list.insert("a");
		assertEquals(1, list.size());
		assertEquals("a", list.get("a"));
	}
	
	@Test
	public void canUseNormal() {
		System.out.println("---");
		list.insert("a");
		list.insert("b");
		list.insert("c");
		assertEquals(3, list.size());

		assertEquals("a", list.get("a"));
		assertEquals("b", list.get("b"));
		assertEquals("c", list.get("c"));
		
		list.remove("a");
		assertEquals(2, list.size());

		list.remove("b");
		assertEquals(1, list.size());

		list.remove("c");
		assertEquals(0, list.size());
	}
	
	@Test
	public void canTwinTower() {
		System.out.println("---");
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.add(0);
		results.add(1);
		results.add(1);
		results.add(0);
		results.add(1);
		
		SkipList<String> mockList = new SkipList<String>(new Comparator<String>() {

			@Override
			public int compare(String op1, String op2) {
				if (op1 == null)
					return -1;
				else if (op2 == null)
					return 1;
				else
					return op1.compareTo(op2);
			}
			
		}, new MockRandom(results), 4);
		
		System.out.println("---");
		mockList.insert("a");
		mockList.insert("b");
		mockList.insert("c");
		assertEquals(3, mockList.size());

		assertEquals("a", mockList.get("a"));
		assertEquals("b", mockList.get("b"));
		assertEquals("c", mockList.get("c"));
		
		mockList.remove("a");
		assertEquals(2, mockList.size());

		mockList.remove("b");
		assertEquals(1, mockList.size());

		mockList.remove("c");
		assertEquals(0, mockList.size());
	}
	
	@Test
	public void canTowerDescent() {
		System.out.println("---");
		ArrayList<Integer> results = new ArrayList<Integer>();
		results.add(0);
		results.add(1);
		results.add(1);
		results.add(1);
		
		SkipList<String> mockList = new SkipList<String>(new Comparator<String>() {

			@Override
			public int compare(String op1, String op2) {
				if (op1 == null)
					return -1;
				else if (op2 == null)
					return 1;
				else
					return op1.compareTo(op2);
			}
			
		}, new MockRandom(results), 4);
		
		System.out.println("---");
		mockList.insert("a");
		mockList.insert("b");
		mockList.insert("c");
		assertEquals(3, mockList.size());

		assertEquals("a", mockList.get("a"));
		assertEquals("b", mockList.get("b"));
		assertEquals("c", mockList.get("c"));
		
		mockList.remove("a");
		assertEquals(2, mockList.size());

		mockList.remove("b");
		assertEquals(1, mockList.size());

		mockList.remove("c");
		assertEquals(0, mockList.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotRetrieveFromEmptyList() {
		assertTrue(list.isEmpty());
		list.get("a");
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotRemoveFromEmptyList() {
		assertTrue(list.isEmpty());
		list.remove("a");
	}
}
