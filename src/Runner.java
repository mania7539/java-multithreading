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
    private void increment() {
        for (int a = 0; a < 10000; a++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();
        try {
            increment();            
        } finally {
            lock.unlock();  // ensure unlock() will always be called
        }
    }

    public void secondThread() throws InterruptedException {
        lock.lock();
        try {
            increment();            
        } finally {
            lock.unlock();  // ensure unlock() will always be called
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
