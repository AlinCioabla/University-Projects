
public class PasswordCrackerTester {

	
	public static void main(String[] args) {
		
		String encodedHash = "14A1E34266BD2A5596A65265D05690E0E2724FD316830D147091BBE606E24DF3"; //24322340

		int numberOfThreads = 69;
		int lowerBound = 10000000;
		int upperBound = 99999999;
		
		Cryptographer sha256Cryptographer = new SHACryptographer("SHA-256");
		PasswordCracker cracker = new NumericPasswordCracker(encodedHash, numberOfThreads,sha256Cryptographer, lowerBound, upperBound);
		
		long startTime = System.currentTimeMillis();

		String output = cracker.Crack();
		
	    long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
		
		
		String name = cracker.GetThreadName();
		
		System.out.println("Password is: " + output);
		System.out.println("Thread " + name + " found it.");
		System.out.println("Elapsed time: " + elapsedTime);
	}
}
  
