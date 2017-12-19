import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running...");
            wait(); // wait() without argument would cause your thread wait infinitely if it's carelessly used
            // wait() function is very resource-efficient. It relinquish the control of the synchronized lock
            System.out.println("Resumed.");
            
        }
    }
    
    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        
        synchronized (this) {
            System.out.println("Waiting for return key.");
            scanner.nextLine();
            System.out.println("Return key pressed.");
            notify(); // notify() the first other thread that calls wait() to get the lock of the object
            // notify() can only be called within a synchronized code block
            // notify() doesn't handle over control of the lock unlike wait(), 
            // the lock is relinquish when synchronized block is completed.
        }
        
        scanner.close();
    }
    
// Output:
//    Producer thread running...
//    Waiting for return key.
//
//    Return key pressed.
//    Resumed.
    
}
