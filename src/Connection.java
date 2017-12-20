import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance = new Connection();
    private int connections = 0;
    private boolean fairness = true;
    private Semaphore semaphore = new Semaphore(10, fairness);
    // fairness is "true" in default
    //  it means that the the first thread calls Semaphore.acquire() 
    //  is guaranteed to get the lock, 
    //  while fairness == "false" will be faster in performance but not guaranteed.
    // So usually we want fairness to be "true".
    
//    private Semaphore semaphore = new Semaphore(10);
    // the fairness is set to "false"
    
    private Connection() {
        
    }
    
    public static Connection getInstance() {
        return instance;
    }
    
    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        try {
            doConnect();
        } finally {
            semaphore.release(); 
            // Use finally to ensure the semaphore releases even when the exception occurs 
        }
    }
    
    public void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        synchronized (this) {
            connections--;
        }
    }
    
// Output:
//    Current connections: 1
//    Current connections: 2
//    Current connections: 3
//    Current connections: 4
//    Current connections: 5
//    Current connections: 6
//    Current connections: 7
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 1
//    Current connections: 2
//    Current connections: 3
//    Current connections: 4
//    Current connections: 5
//    Current connections: 6
//    Current connections: 7
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 7
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 5
//    Current connections: 6
//    Current connections: 7
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    ...
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 10
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
//    Current connections: 8
//    Current connections: 9
//    Current connections: 10
}
