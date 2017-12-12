import java.util.LinkedList;

public class ProducerConsumer extends PetriNet {

	public ProducerConsumer(int numberOfProducers, int numberOfConsumers, int freeSpotsNumber) {
		super();
		initialize(numberOfProducers, numberOfConsumers, freeSpotsNumber);
	}

	public void run() {

	}

	private void initialize(int numberOfProducers, int numberOfConsumers, int freeSpotsNumber) {

		Location producers = new Location("Producers", numberOfProducers);
		Location consumers = new Location("Consumers", numberOfConsumers);
		Location elementProduced = new Location("ElementReadyWrite", 0);
		Location elementConsumed = new Location("ElementReadyRead", 0);
		Location freeSpots = new Location("FreeSpots", freeSpotsNumber);
		Location fullSpots = new Location("FullSpots", 0);
		Location mutex = new Location("Mutex", 1);

		mTransitions.add(new Transition("Produce", new LinkedList<Arc>() {
			{
				add(new Arc(1, producers, Direction.OUT));
				add(new Arc(1, elementProduced, Direction.IN));
			}
		}));

		mTransitions.add(new Transition("Push", new LinkedList<Arc>() {
			{
				add(new Arc(1, elementProduced, Direction.OUT));
				add(new Arc(1, mutex, Direction.OUT));
				add(new Arc(1, mutex, Direction.IN));
				add(new Arc(1, freeSpots, Direction.OUT));
				add(new Arc(1, fullSpots, Direction.IN));
			}
		}));

		mTransitions.add(new Transition("Pop", new LinkedList<Arc>() {
			{
				add(new Arc(1, mutex, Direction.IN));
				add(new Arc(1, mutex, Direction.OUT));
				add(new Arc(1, freeSpots, Direction.IN));

			}
		}));

	}

}
