import java.util.Random;

public class App {
	public static void main(String[] args) throws InterruptedException {
	    System.out.println("Starting.");
	    
		Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Random random = new Random();
                
                // 1E6 === 1*10^6 === (1 multiply 10 to the power of 6)
                for (int i=0; i<1E8; i++) {
                    Math.sin(random.nextDouble());
                    // 1E8 iterations of sin() will take about 5 seconds 
                }
            }
		    
		});
		
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt(); 
		// Thread.interrupt() doesn't stop a thread
		//    Thread.interrupt() just sets a flag to tell a thread that it's been interrupted.
		//	  and there is a Thread.stop() which is deprecated
		// Thread.interrupt() should work with 
		
		t1.join();
		
		System.out.println("Finished.");
		
	}
	
// output:
//	Starting.
//	Finished.

}
