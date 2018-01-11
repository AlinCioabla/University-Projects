import java.util.concurrent.Semaphore;

public class CounterIncrementer implements Runnable{

	private Semaphore sem;
	private int id;
	
	public CounterIncrementer(Semaphore sem, int id) {
		this.sem = sem;
		this.id = id;
	}
	
	@Override
	public void run() {
		while(true)
		try {
			sem.acquire();
			Thread.sleep(500);
			if(Counter.getValue() <= Counter.limit) {
				System.out.println(String.format("Current count: %d , now being incremented by thread %d", Counter.getValue(), id));
				Counter.increment();
			}
			else {
				break;
			}

			sem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		
		}

		
		
	}

}
