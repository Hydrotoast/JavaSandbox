package entities;

import util.EagerResourcePool;
import util.Reusable;

public class PooledEntity extends Reusable {

	public PooledEntity(EagerResourcePool<Reusable> pool) {
		super(pool);
	}
	
}
