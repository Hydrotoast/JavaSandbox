package edu.giocc.util;

public interface Observable {
	public abstract void registerObserver(Observer o);
	public abstract void updateAll();
}
