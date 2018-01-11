import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class FaultTolerantCounting {
	
	private List<Thread> threads;
	private final Semaphore sem;

	
	public FaultTolerantCounting(int threadNumber) {
		threads = new LinkedList<Thread>();
		sem = new Semaphore(1,true);
		
		for(int i = 1; i <= 4; i++) {
			threads.add(new Thread(new CounterIncrementer(sem,i)));
		}

	}
	
	public void start() throws InterruptedException {
		for(Thread t : threads) {
			t.start();
		}
		for(Thread t : threads) {
			t.join();
		}
	}
}
