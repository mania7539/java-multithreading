import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	private Random random = new Random();

	public void stageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
		
	}

	public void stageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
		
	}

	public void process() {
		for (int i=0;i<1000;i++) {
			stageOne();
			stageTwo();
			
		}
	} 
	
	public void main() {
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});
		
		t1.start();
		try {
			t1.join(); // wait t1 to finish before the below lines can be implemented
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " +list2.size());

// Output:		
//		Starting...
//		Time take: 2683
//		List1: 1000; List2: 1000
	}
}
