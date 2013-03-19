package edu.giocc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.giocc.util.QueueList;

public class LinkedQueueTests {
	private QueueList<Integer> queue;

	@Before
	public void setUp() throws Exception {
		queue = new QueueList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		while (!queue.isEmpty())
			queue.dequeue();
	}

	@Test
	public void canInitialize() {
		assertTrue(queue.isEmpty());
	}

	@Test
	public void canEnqueueFirst() {
		queue.enqueue(11);
		assertEquals(1, queue.size());
		assertEquals(11, queue.peek().intValue());

		queue.dequeue();
		assertEquals(0, queue.size());

		queue.enqueue(22);
		assertEquals(1, queue.size());
		assertEquals(22, queue.peek().intValue());
	}

	@Test
	public void canPollWithQueue() {
		queue.enqueue(11);
		assertEquals(1, queue.size());
		assertEquals(11, queue.peek().intValue());
		
		queue.enqueue(22);
		assertEquals(2, queue.size());
		assertEquals(11, queue.peek().intValue());
		
		assertEquals(11, queue.poll().intValue());
		assertEquals(1, queue.size());
		assertEquals(22, queue.poll().intValue());
		assertTrue(queue.isEmpty());
		
		assertNull(queue.poll());
		assertNull(queue.poll());

		queue.enqueue(33);
		assertEquals(33, queue.peek().intValue());
		assertEquals(1, queue.size());
		assertFalse(queue.isEmpty());
		
		assertEquals(33, queue.poll().intValue());
		assertTrue(queue.isEmpty());
		assertNull(queue.poll());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void cannotPeekWithEmptyQueue() {
		queue.peek();
	}

	@Test(expected = NoSuchElementException.class)
	public void cannotDequeueWithEmptyQueue() {
		queue.dequeue();
	}
}
