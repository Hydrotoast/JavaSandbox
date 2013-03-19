
public class CoroutineManager<T> {
	private Producer<T> producer;
	private Consumer<T> consumer;
	
	public CoroutineManager(Producer<T> producer, Consumer<T> consumer) {
		this.producer = producer;
		this.consumer = consumer;
	}
	
	public void run() {
		while (!producer.isDone()) {
			T data = producer.produce();
			consumer.consume(data);
		}
	}
}
