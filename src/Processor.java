import java.util.LinkedList;
import java.util.Random;

public class Processor {
    private LinkedList<Integer> list = new LinkedList<>(); // our data store
    private final int LIMIT = 10;
    private Object lock = new Object();
    
    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                
                // keep waiting when the list.size() == LIMIT
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
                // notify() when about to leave the synchronized block
            }
            
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        
        while (true) {
            synchronized (lock) {
                
                // keep waiting when the list.size() == 0
                while (list.size() == 0) {
                    lock.wait();
                }
                
                System.out.print("List size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);        
                lock.notify();
                // notify() when about to leave the synchronized block
            }
            
            Thread.sleep(random.nextInt(1000));
        }
    }
    
    
// Output:
//    List size is: 10; value is: 0
//    List size is: 10; value is: 1
//    List size is: 10; value is: 2
//    List size is: 10; value is: 3
//    List size is: 10; value is: 4
//    List size is: 10; value is: 5
//    List size is: 10; value is: 6
//    List size is: 10; value is: 7
//    List size is: 10; value is: 8
//    List size is: 10; value is: 9
//    List size is: 10; value is: 10
//    List size is: 10; value is: 11
//    List size is: 10; value is: 12
//    List size is: 10; value is: 13
//    List size is: 10; value is: 14
//    List size is: 10; value is: 15
//    List size is: 10; value is: 16
//    List size is: 10; value is: 17
//    List size is: 10; value is: 18
//    List size is: 10; value is: 19
//    List size is: 10; value is: 20
//    List size is: 10; value is: 21
//    List size is: 10; value is: 22
//    List size is: 10; value is: 23
//    List size is: 10; value is: 24
//    List size is: 10; value is: 25
//    ...
}
