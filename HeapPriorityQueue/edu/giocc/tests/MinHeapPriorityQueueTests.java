package edu.giocc.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.queue.MinHeapPriorityQueue;

public class MinHeapPriorityQueueTests {
	MinHeapPriorityQueue<Integer> queue;

	@Before
	public void setUp() throws Exception {
		queue = new MinHeapPriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}

		});
	}
	
	@Test
	public void canInitialize() {
		assertTrue(queue.isEmpty());
	}

	@Test
	public void canEnqueueRoot() {
		queue.enqueue(1);
		assertEquals(1, queue.size());
		try {
			assertEquals(1, queue.peek().intValue());
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void canEnqueueMaximum() {
		// bubbleDown
		queue.enqueue(1);
		queue.enqueue(2);
		assertEquals(2, queue.size());
		try {
			assertEquals(1, queue.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void canEnqueueMinimum() {
		// bubbleUp
		queue.enqueue(2);
		queue.enqueue(1);
		assertEquals(2, queue.size());
		try {
			assertEquals(1, queue.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void canEnqueueMany() {
		// bubbleUp
		queue.enqueue(1);
		queue.enqueue(5);
		queue.enqueue(10);
		try {
			queue.dequeue();
			queue.enqueue(11);
			assertEquals(3, queue.size());
			assertEquals(5, queue.peek().intValue());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void canDequeueMaximum() {
		// bubbleDown
		queue.enqueue(1);
		try {
			queue.dequeue();
			assertEquals(0, queue.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void cannotDequeueFromEmptyQueue() {
		queue.dequeue();
	}
}
