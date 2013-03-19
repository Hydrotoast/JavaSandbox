package edu.giocc.util;

public interface DynamicObservable {
	public abstract void registerObserver(Class<? extends ObservableEvent> e, DynamicObserver o);

	public abstract void updateAll(Class<? extends ObservableEvent> e);
}
