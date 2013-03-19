package entities;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import lang.EmptyPoolException;

import util.EagerResourcePool;
import util.Reusable;


public class EntityPool implements EagerResourcePool<Reusable> {
	private static EntityPool instance;
	private Queue<Reusable> availableResources;

	private EntityPool() {
		availableResources = new LinkedList<Reusable>();
		preloadResources();
	}
	
	public static EagerResourcePool<Reusable> getInstance() {
		if (instance == null)
			instance = new EntityPool();
		return instance;
	}

	@Override
	public Reusable acquireResource() throws EmptyPoolException {
		try {
			Reusable data = availableResources.remove();
			return data;
		} catch (NoSuchElementException ex) {
			throw new EmptyPoolException();
		}
	}

	@Override
	public void releaseResource(Reusable resource) {
		availableResources.add(resource);
	}

	public void preloadResources() {
		//availableResources.add(new LogicalProcess(this, 10));
		//availableResources.add(new LogicalProcess(this, 15));
	}
}