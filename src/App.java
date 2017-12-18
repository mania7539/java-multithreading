import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    // ArrayBlockingQueue is a thread-safe data structure which can hold data items,
    // the type of which you can choose as with an ArrayList, you can add/remove
    // items to it.
    //
    // it has thread-safe methods put, take to deal with producer-consumer patterns
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
// Output:
//        Taken value: 42; Queue size is: 10
//        Taken value: 9; Queue size is: 9
//        Taken value: 25; Queue size is: 9
//        Taken value: 20; Queue size is: 9
//        Taken value: 58; Queue size is: 10
//        Taken value: 17; Queue size is: 9
//        Taken value: 75; Queue size is: 9
//        Taken value: 1; Queue size is: 9
//        ...
    }

    // you will have 1 ore more threads producing / consuming things which share a
    // common data
    private static void producer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            queue.put(random.nextInt(100));
            // put will suppose the queue be full after queue contains 10 items,
            // then it will patiently wait until items are removed from the queue so 
            // the size is <=10 and there's space in the queue and then it executes.
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();

        while (true) {
            Thread.sleep(100);
            
            if (random.nextInt(10) == 0) {
                Integer value = queue.take(); 
                // if nothing is in the queue, then take will wait until something is added to the queue
                // and it doesn't consume too many resources while it's waiting or anything like that.
                
                System.out.printf("Taken value: %d; Queue size is: %d\n", value, queue.size()); 
            }
        }
    }
}
