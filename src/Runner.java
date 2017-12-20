import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    
    public void firstThread() throws InterruptedException {
        Random random = new Random();

        for (int a = 0; a < 10000; a++) {
            lock1.lock();
            lock2.lock();
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
            lock1.lock();
            lock2.lock();
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
//    Account 1 balance: 8079
//    Account 2 balance: 11921
//    Total balance: 20000
}
