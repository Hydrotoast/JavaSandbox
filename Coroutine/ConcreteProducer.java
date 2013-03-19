
public class ConcreteProducer<T> implements Producer<T> {
	private Generator<T> generator;
	
	public ConcreteProducer() {
		generator = new Generator<T>() {

			@Override
			public GeneratorState execute() {
				// calculate value
				
				// if value: return GeneratorState.YIELD;
				// if no more values: return GeneratorState.BREAK;
				return GeneratorState.CONTINUE;
			}
			
		};
	}
	
	@Override
	public boolean isDone() {
		return generator.isDone();
	}

	@Override
	public T produce() {
		return generator.iterate();
	}

}
