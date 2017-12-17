
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }

}

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Give out 5 tasks to 2 executors for completion
        for (int i=0; i<5; i++) {
            executor.submit(new Processor(i));
        }
        
        // Shutdown the executor: 
        //  ExecutorService won't shutdown immediately, but wait for all the threads to 
        //  complete doing what they're doing and then they will terminate.
        executor.shutdown();
        
        System.out.println("All tasks submitted.");
        
        // Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or 
        // force ExecutorService to terminate all threads in a given time frame
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // You can execute more tasks with ExecutorService after awaitTermination below.
        System.out.println("All tasks completed.");
        
        
// Output:
//        Starting: 1
//        Starting: 0
//        All tasks submitted.
//        Completed: 0          // Each time the task is completed by a thread, 
//        Completed: 1          // the executor will assign a new task to the thread until all tasks are done.
//        Starting: 2
//        Starting: 3
//        Completed: 3
//        Completed: 2
//        Starting: 4
//        Completed: 4
//        All tasks completed.
                                // There's a lot of overhead starting threads, 
                                // and by recycling threads in this thread pool, you avoid that overhead.
        
    }
}
