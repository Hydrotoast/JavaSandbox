package edu.giocc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.DoublyLinkedList;

public class DoublyLinkedListTests {
	private DoublyLinkedList<Integer> list;
	
	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		list.clear();
	}

	@Test
	public void canUseFirst() {
		list.addFirst(11);
		assertEquals(11, list.getFirst().intValue());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		list.removeFirst();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}


	@Test
	public void canUseLast() {
		list.addLast(11);
		assertEquals(11, list.getLast().intValue());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		list.removeLast();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}

	@Test ( expected = NoSuchElementException.class )
	public void cannotGetFirstFromEmptyList() throws NoSuchElementException {
		assertTrue(list.isEmpty());
		list.getFirst();
	}
	
	@Test ( expected = NoSuchElementException.class )
	public void cannotGetLastFromEmptyList() throws NoSuchElementException {
		assertTrue(list.isEmpty());
		list.getLast();
	}

	@Test ( expected = NoSuchElementException.class )
	public void cannotGetAnyFromEmptyList() throws NoSuchElementException {
		assertTrue(list.isEmpty());
		list.get(11);
	}
	
	@Test ( expected = NoSuchElementException.class )
	public void cannotRemoveAnyFromEmptyList() throws NoSuchElementException {
		assertTrue(list.isEmpty());
		list.remove(11);
	}
	
	@Test
	public void canUseAnyFromList() {
		list.addFirst(11);
		list.addFirst(22);
		list.addFirst(33);
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(33, list.get(0).intValue());
		assertEquals(22, list.get(1).intValue());
		assertEquals(11, list.get(2).intValue());
		
		list.remove(33);
		list.remove(22);
		list.remove(11);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void canUseAsStack() {
		list.addFirst(11);
		list.addFirst(22);
		list.addFirst(33);
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(33, list.getFirst().intValue());
		list.removeFirst();
		
		assertEquals(22, list.getFirst().intValue());
		list.removeFirst();
		
		assertEquals(11, list.getFirst().intValue());
		list.removeFirst();
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void canUseAsQueue() {
		list.addLast(11);
		list.addLast(22);
		list.addLast(33);
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());
		
		assertEquals(11, list.getFirst().intValue());
		list.removeFirst();

		assertEquals(22, list.getFirst().intValue());
		list.removeFirst();

		assertEquals(33, list.getFirst().intValue());
		list.removeFirst();
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void canUseIterator() {
		list.addLast(11);
		list.addLast(22);
		list.addLast(33);
		assertEquals(3, list.size());
		assertFalse(list.isEmpty());

		Iterator<Integer> iter = list.iterator();
		
		assertTrue(iter.hasNext());
		assertEquals(11, iter.next().intValue());
		iter.remove();
		assertEquals(2, list.size());
		
		assertTrue(iter.hasNext());
		assertEquals(22, iter.next().intValue());
		iter.remove();
		assertEquals(1, list.size());
		
		assertTrue(iter.hasNext());
		assertEquals(33, iter.next().intValue());
		iter.remove();
		assertEquals(0, list.size());
	}
	
	@Test
	public void cannotIterateWithEmptyList() {
		assertTrue(list.isEmpty());
		Iterator<Integer> iter = list.iterator();
		assertFalse(iter.hasNext());
	}
	
	@Test ( expected = IllegalStateException.class )
	public void cannotRemoveMoreThanOnceWithIterator() {
		list.addLast(11);
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		
		Iterator<Integer> iter = list.iterator();
		
		assertTrue(iter.hasNext());
		assertEquals(11, iter.next().intValue());
		iter.remove();
		iter.remove();
	}
}
