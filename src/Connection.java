
public class Connection {
    private static Connection instance = new Connection();
    private int connections = 0;
    
    private Connection() {
        
    }
    
    public static Connection getInstance() {
        return instance;
    }
    
    public void connect() {
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
//    Current connections: 11
//    Current connections: 12
//    Current connections: 13
//    ...
//    Current connections: 181
//    Current connections: 182
//    Current connections: 183
//    Current connections: 184
//    Current connections: 185
//    Current connections: 186
//    Current connections: 187
//    Current connections: 188
//    Current connections: 189
//    Current connections: 190
//    Current connections: 191
//    Current connections: 192
//    Current connections: 193
//    Current connections: 194
//    Current connections: 195
//    Current connections: 196
//    Current connections: 197
//    Current connections: 198
//    Current connections: 199
//    Current connections: 200
}
