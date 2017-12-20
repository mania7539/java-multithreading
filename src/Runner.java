import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    
    /**
     * A kind of method that can call locks in any order.
     * It will unlock all locks and lock them with the same order, or it will loop again.
     * 
     * When locks are locked in the same order which is defined in this function, 
     *  the loop will end, and the function will return;
     */
    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            // Acquire locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            
            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
                // All of above which is successfully locked, should be unlocked in the 1st iteration.
                //  The 2nd iteration will force the locks to be locked in the same order.
                //
                // If another thread is using this function before the current thread leaves, 
                //  it will be kept in the loop until the locks are unlocked.
                
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                }
                
                if (gotFirstLock) {
                    firstLock.unlock();
                }
                
                if (gotSecondLock) {
                    secondLock.unlock();
                }
                
                if (!gotFirstLock && !gotSecondLock) {}
            }
            
            // Locks not acquired
            Thread.sleep(1);
        }
    }
    
    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int a = 0; a < 10000; a++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));                
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();

        for (int a = 0; a < 10000; a++) {
            acquireLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));                
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 balance: " + account1.getBalance());
        System.out.println("Account 2 balance: " + account2.getBalance());

        System.out.println("Total balance: " + (account1.getBalance() + account2.getBalance()));
    }

// Output:
//    Account 1 balance: 17227
//    Account 2 balance: 2773
//    Total balance: 20000
}
