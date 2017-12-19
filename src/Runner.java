import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    // once a thread acquires this lock (once a thread lock this lock),
    //  it can lock again if it wants to (the same thread) and the lock just keeps count how many times it's been locked, 
    //  and then you have to unlock it (the same thread) by the same number of times
    //
    // it works like synchronized block, but in some circumstances it has some advantages
    // 
    private Condition condition = lock.newCondition();
    
    private void increment() {
        for (int a = 0; a < 10000; a++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();  // like wait()
        
        System.out.println("Woken up!");
        try {
            increment();            
        } finally {
            lock.unlock();  // ensure unlock() will always be called
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        
        lock.lock();
        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");
        
        condition.signal(); // like notify(), but you still have to unlock() since it won't relinquish the lock
        try {
            increment();            
        } finally {
            lock.unlock();  // ensure unlock() will always be called
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
    
// Output:
//    Waiting...
//    Press the return key!
//
//    Got return key!
//    Woken up!
//    Count is: 20000

}
