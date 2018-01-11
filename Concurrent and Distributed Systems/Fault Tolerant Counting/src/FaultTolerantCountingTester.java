
public class FaultTolerantCountingTester {

	public static void main(String[] args) {
		
		FaultTolerantCounting ftc = new FaultTolerantCounting(4);
		try {
			ftc.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
