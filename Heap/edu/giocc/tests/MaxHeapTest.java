package edu.giocc.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.heap.MaxHeap;


public class MaxHeapTest {
	MaxHeap<Integer> heap;

	@Before
	public void setUp() throws Exception {
		heap = new MaxHeap<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}

		});
	}
	
	@After
	public void tearDown() throws Exception {
		while (!heap.isEmpty())
			heap.remove();
	}
	
	@Test
	public void canInitialize() {
		assertTrue(heap.isEmpty());
	}

	@Test
	public void canInsertElementForRoot() {
		heap.add(1);
		assertEquals(1, heap.size());
		try {
			assertEquals(1, heap.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void canGetMaxElement() {
		heap.add(1);
		try {
			assertEquals(1, heap.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void cannotGetRootFromEmptyHeap() {
		heap.peek();
	}

	@Test
	public void canRemoveMaxElement() {
		heap.add(1);
		try {
			heap.remove();
			assertEquals(0, heap.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Bubble up
		heap.add(1);
		heap.add(2);
		try {
			heap.remove();
			assertEquals(1, heap.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// Bubble down
		heap.add(2);
		heap.add(1);
		try {
			heap.remove();
			assertEquals(1, heap.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void cannotRemoveRootFromEmptyHeap() {
		heap.remove();
	}

	@Test
	public void canInsertBiggerElementAfterRoot() {
		// bubbleUp
		heap.add(1);
		heap.add(2);
		assertEquals(2, heap.size());
	}

	@Test
	public void canInsertSmallerElementAfterRoot() {
		// bubbleDown
		heap.add(2);
		heap.add(1);
		assertEquals(2, heap.size());
	}
	
	@Test
	public void canInsertSeveralAfterRoot() {
		heap.add(55);
		heap.add(60);
		heap.add(50);

		heap.add(5);
		heap.add(10);
		assertEquals(5, heap.size());
		
		assertEquals(60, heap.peek().intValue());
		heap.remove();
		
		assertEquals(55, heap.peek().intValue());
		heap.remove();
		
		assertEquals(50, heap.peek().intValue());
		heap.remove();

		assertEquals(10, heap.peek().intValue());
		heap.remove();

		assertEquals(5, heap.peek().intValue());
		heap.remove();
	}
}
