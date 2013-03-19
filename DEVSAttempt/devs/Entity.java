package devs;

public abstract class Entity {
	protected int timeCell;
	protected int prevTime;
	protected int util;
	protected int entityNum;
	protected boolean avail;

	public abstract void schedule();
	public abstract void release();
	public abstract void nextB();	
}
