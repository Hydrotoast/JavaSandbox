package util;


public class Reusable {
	private static int idCount = 0;
	private int id;
	
	private EagerResourcePool<Reusable> pool;
	
	public Reusable(EagerResourcePool<Reusable> pool) {
		assert pool != null;
		
		id = idCount++;
		this.pool = pool;
	}
	
	public int getId() {
		return id;
	}
	
	public void release() {
		pool.releaseResource(this);
	}
}
