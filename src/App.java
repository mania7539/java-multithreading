import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
    private CountDownLatch latch; // CountDownLatch is a thread-safe class

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started. ");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown(); // the latch will be counted down by 1
    }

}

public class App {
    public static void main(String[] args) {

        // a CountDownLatch lets you count down from a number "3" that you specify,
        // it lets one or more threads wait until the latch reaches a common 0.
        // So one or more threads can count down the latch and when it finally equal 0,
        // then one or more threads they're waiting on the latch will proceed and carry on.
        //
        // this can be one sometimes very simple into thread communication
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService excutors = Executors.newFixedThreadPool(3);

        for (int a = 0; a < 3; a++) {
            excutors.submit(new Processor(latch));
        }
        
        // latch.await will wait until latch.countDown has reached "0"
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        System.out.println("Completed.");
        
// Output:
//        Started. 
//        Started. 
//        Started. 
//        Completed.

    }
}
