import java.util.LinkedList;
import java.util.Scanner;

public class ProducerConsumer extends PetriNet {

	public ProducerConsumer(int numberOfProducers, int numberOfConsumers, int freeSpotsNumber) {
		super();
		locations = new LinkedList<Location>();
		initialize(numberOfProducers, numberOfConsumers, freeSpotsNumber);
	}

	public void run() {

		System.out.println("Elements ready for consume: " + locations.get(0).GetJetoane());
		System.out.println("Element ready for produce: " + locations.get(1).GetJetoane());
		System.out.println("Freespots" + locations.get(2).GetJetoane());
		System.out.println("Fullspots" + locations.get(3).GetJetoane());

		Scanner scanner = new Scanner(System.in);
		String action = scanner.nextLine();

		while (!action.equals("exit")) {
			Exec(action);
			System.out.println("Elements ready for consume: " + locations.get(0).GetJetoane());
			System.out.println("Element ready for produce: " + locations.get(1).GetJetoane());
			System.out.println("Freespots" + locations.get(2).GetJetoane());
			System.out.println("Fullspots" + locations.get(3).GetJetoane());

			action = scanner.nextLine();
		}

	}

	private void initialize(int numberOfProducers, int numberOfConsumers, int freeSpotsNumber) {

		Location producers = new Location("Producers", numberOfProducers);
		Location consumers = new Location("Consumers", numberOfConsumers);
		Location elementProduced = new Location("ElementReadyProduce", 0);
		Location elementConsumed = new Location("ElementReadyConsume", 0);
		Location freeSpots = new Location("FreeSpots", freeSpotsNumber);
		Location fullSpots = new Location("FullSpots", 0);
		Location mutex = new Location("Mutex", 1);

		locations.add(elementProduced);
		locations.add(elementConsumed);
		locations.add(freeSpots);
		locations.add(fullSpots);

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
				add(new Arc(1, fullSpots, Direction.OUT));
				add(new Arc(1, elementConsumed, Direction.IN));
				add(new Arc(1, consumers, Direction.OUT));

			}
		}));

		mTransitions.add(new Transition("Consume", new LinkedList<Arc>() {
			{
				add(new Arc(1, elementConsumed, Direction.OUT));
				add(new Arc(1, consumers, Direction.IN));
			}
		}));

	}

	LinkedList<Location> locations;
}
