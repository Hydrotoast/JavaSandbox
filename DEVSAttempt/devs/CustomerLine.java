package devs;

public class CustomerLine extends PriorityQueue implements Observable {
	private Observer observer;

	@Override
	public void addObserver(Observer o) {
		this.observer = o;
	}

	@Override
	public void removeObserver(Observer o) {
		this.observer = null;
	}

	@Override
	public void notifyObservers() {
		this.observer.update(this);
	}

}
