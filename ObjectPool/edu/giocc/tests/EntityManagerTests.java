package edu.giocc.tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.giocc.lang.EmptyPoolException;
import edu.giocc.util.EntityManager;
import edu.giocc.util.Reusable;


public class EntityManagerTests {
	
	@Test
	public void canAcquireResources() {
		Reusable resource0 = EntityManager.getInstance().acquireResource();
		assertEquals(0, resource0.getId());
		
		Reusable resource1 = EntityManager.getInstance().acquireResource();
		assertEquals(1, resource1.getId());
		
		resource0.release();
		resource1.release();
	}
	
	@Test
	public void canReleaseResourcesInOrder() {
		Reusable resource0 = EntityManager.getInstance().acquireResource();
		resource0.release();
		
		Reusable resource1 = EntityManager.getInstance().acquireResource();
		assertEquals(1, resource1.getId());
		resource1.release();
	}
	
	@Test ( expected = EmptyPoolException.class)
	public void cannotAcquireMoreResourcesThanAvailable() {
		Reusable resource = EntityManager.getInstance().acquireResource();
		assertEquals(0, resource.getId());

		resource = EntityManager.getInstance().acquireResource();
		resource = EntityManager.getInstance().acquireResource();
	}

}
