import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// we want to get the time (Integer) as the thread.sleep returns
		    // you can also use ArrayList<Future<Integer>> to store the entire submit() results
		Future<Integer> future = executor.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                
                System.out.println("Starting...");
                
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                

                System.out.println("Finished.");
                return duration;    
            }
		    
		});
		
		executor.shutdown();
		try {
            System.out.println("Result is: " + future.get());
            // future.get() will still block until the result is on, if you don't make executor to wait
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
	}
	
// output:
//	Starting...
//	Finished.
//	Result is: 581
}
