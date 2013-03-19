package edu.giocc.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import edu.giocc.lang.EmptyPoolException;

public class EntityManager implements EagerResourcePool<Reusable> {
	private static EntityManager instance;
	private Queue<Reusable> availableResources;

	private EntityManager() {
		availableResources = new LinkedList<Reusable>();
		preloadResources();
	}

	public static EagerResourcePool<Reusable> getInstance() {
		if (instance == null)
			instance = new EntityManager();
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

	private void preloadResources() {
		availableResources.add(new Reusable(this));
		availableResources.add(new Reusable(this));
	}
}
