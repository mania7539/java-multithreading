import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws InterruptedException {
	    ExecutorService executor = Executors.newCachedThreadPool();
	    // Executors.newCachedThreadPool() will create a new thread every time you call submit()
	    //     and it will try to reuse threads.
	    
	    for (int a=0; a<200; a++) {
	        executor.submit(new Runnable() { 
                @Override
                public void run() {
                    Connection.getInstance().connect();
                    
                }
            });
	    }
	    
	    executor.shutdown();
	    executor.awaitTermination(1, TimeUnit.DAYS);
	}

}
