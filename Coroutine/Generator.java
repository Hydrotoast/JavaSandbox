public abstract class Generator<T> {
	protected enum GeneratorState {
		CONTINUE, YIELD, BREAK;
	}

	private GeneratorState state;
	private boolean isDone;
	private T retval;

	public Generator() {
		isDone = false;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	protected T getRetval() {
		return retval;
	}
	
	protected void setRetval(T retval) {
		this.retval = retval;
	}

	public T iterate() {
		for (;;) {
			state = execute();
			switch (state) {
			case YIELD:
				return getRetval();
			case BREAK:
				isDone = true;
				return null;
			}
		}
	}

	public abstract GeneratorState execute();
}
