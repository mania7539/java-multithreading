import java.util.concurrent.Semaphore;

public class App {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(1);   // use 0 or 1 for permits would be reasonable
		
		semaphore.release();  // semaphore.availablePermits() +1
		semaphore.acquire();  // semaphore.availablePermits() -1
		// If semaphore.availablePermits() == 0, acquire() would wait until a permit is released
		
		System.out.println("Available permits: " + semaphore.availablePermits());
	}
	
// Output:
//	Available permits: 1

}
