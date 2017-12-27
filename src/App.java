import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(new Runnable() {
		    public void run() {
		        Random random = new Random();
		        int duration = random.nextInt(4000);
		        
		        System.out.println("Starting...");
		        
		        try {
                    Thread.sleep(duration);
                    // Question: how to get the exact system sleep time in a clean way?
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		        

		        System.out.println("Finished.");
		    }
		    
		});
		
		executor.shutdown();
	}
	
// output:
//	Starting...
//	Finished.
}
