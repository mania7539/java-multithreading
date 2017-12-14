import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	private Random random = new Random();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void stageOne() {
		synchronized (lock1) { // it's a separate implementation, can use list1 as lock1, but not a better practice
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}

	public void stageTwo() {
		synchronized (lock2) { // it's a separate implementation, can use list2 as lock2, but not a better practice
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}

	public void process() {
		for (int i = 0; i < 1000; i++) {
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
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				process();
			}
		});

		t1.start();
		t2.start();
		try {
			t1.join(); // wait t1 to finish before the below lines can be implemented
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());

// Output:
//		Starting...
//		Time take: 2689
//		List1: 2000; List2: 2000
	}
}
