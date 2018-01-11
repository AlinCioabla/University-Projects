import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
	public static AtomicInteger count = new AtomicInteger(0);
	
	public static void increment() {
		count.addAndGet(1);
	}
	
	public static int getValue() {
		return count.get();
	}
	
	public static final int limit = 69;
}
