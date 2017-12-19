import java.util.LinkedList;
import java.util.Scanner;

public class ReaderWriter extends PetriNet {

	public ReaderWriter(int workers, int reading, int writing) {
		super();
		initialize(workers, reading, writing);
	}

	public void run() {

		Scanner scanner = new Scanner(System.in);
		String action = scanner.nextLine();

		while (!action.equals("exit")) {
			Exec(action);
			action = scanner.nextLine();
		}

	}

	private void initialize(int nworkers, int nreading, int nwriting) {

		Location workers = new Location("workers", nworkers);
		Location reading = new Location("reading", nreading);
		Location writing = new Location("Writing", nwriting);

		Arc arrowWorkersOutToReading = new Arc(3, workers, Direction.OUT);
		Arc arrowWorkersInFromReading = new Arc(3, workers, Direction.IN);

		Arc arrowReadingInFromWorkers = new Arc(3, reading, Direction.IN);
		Arc arrowReadingOutToWorkers = new Arc(3, reading, Direction.IN);

		Arc arrowWorkersOutToWriting = new Arc(3, workers, Direction.OUT);
		Arc arrowWorkersInFromWriting = new Arc(3, workers, Direction.IN);

		Arc arrowWritingInFromWorkers = new Arc(3, reading, Direction.IN);
		Arc arrowWritingOutToWorkers = new Arc(3, reading, Direction.OUT);

		Transition startRead = new Transition("StartRead", new LinkedList<Arc>() {
			{
				add(arrowWorkersOutToReading);
				add(arrowReadingInFromWorkers);
			}
		});

		Transition endRead = new Transition("EndRead", new LinkedList<Arc>() {
			{
				add(arrowReadingOutToWorkers);
				add(arrowWorkersInFromReading);
			}
		});

		Transition startWrite = new Transition("StartWrite", new LinkedList<Arc>() {
			{
				add(arrowWorkersOutToWriting);
				add(arrowWritingInFromWorkers);
			}
		});

		Transition endWrite = new Transition("EndWrite", new LinkedList<Arc>() {
			{
				add(arrowWritingOutToWorkers);
				add(arrowWorkersInFromWriting);
			}
		});

		mTransitions.add(startRead);
		mTransitions.add(endRead);
		mTransitions.add(startWrite);
		mTransitions.add(endWrite);

	}

}
