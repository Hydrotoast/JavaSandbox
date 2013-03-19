package edu.giocc.tests;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.SinglyLinkedList;

public class SinglyLinkedListTests {
	private SinglyLinkedList<Integer> list;
	
	@Before
	public void setUp() {
		list = new SinglyLinkedList<Integer>();
	}
	
	@After
	public void tearDown() {
		list.clear();
	}
	
	@Test
	public void canInitialize() {
		assertEquals(0, list.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotGetFirstFromEmptyList() throws NoSuchElementException {
		list.getFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotGetLastFromEmptyList() throws NoSuchElementException {
		list.getLast();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotRemoveFirstFromEmptyList() throws NoSuchElementException {
		list.removeFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotRemoveLastFromEmptyList() throws NoSuchElementException {
		list.removeLast();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void cannotRemoveAnyFromEmptyList() throws NoSuchElementException {
		assertEquals(0, list.size());
		list.remove(11);
	}
	
	@Test
	public void canGetFirstWithSingleElement() {
		list.addFirst(11);
		assertEquals(1, list.size());
		assertEquals(11, list.getFirst().intValue());
		
		list.removeFirst();
		assertEquals(0, list.size());

		list.addLast(11);
		assertEquals(1, list.size());
		assertEquals(11, list.getFirst().intValue());
	}
	
	@Test
	public void canGetLastWithSingleElement() {
		list.addFirst(11);
		assertEquals(1, list.size());
		assertEquals(11, list.getLast().intValue());
		
		list.removeLast();
		assertEquals(0, list.size());

		list.addLast(11);
		assertEquals(1, list.size());
		assertEquals(11, list.getLast().intValue());
	}

//	private void populateTriple() {
//		list.addFirst(11);
//		list.addFirst(22);
//		list.addFirst(33);
//	}
}

