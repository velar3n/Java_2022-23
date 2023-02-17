import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Entrances {
	private boolean firstEntryFree;
	private boolean secondEntryFree;
	private ExecutorService execut = Executors.newCachedThreadPool();
	private Lock lock;
	private Condition passingEntrance;

	public Entrances() {
		firstEntryFree = true;
		secondEntryFree = true;
		lock = new ReentrantLock(true);
		passingEntrance = lock.newCondition();
	}

	public void symulate(int n, WriteToFile writeToFile) {
		for (int i = 1; i <= n; i++)
			execut.execute(new Bee(this, i, writeToFile));
	}

	/*public void passFirstEntrance() {
		try {
			if(lock.tryLock()) {
				firstEntryFree = false;
				passingEntrance.await(1, TimeUnit.SECONDS);
				passingEntrance.signal();
				firstEntryFree = true;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void passSecondEntrance() {
		try {
			if(lock.tryLock()) {
				secondEntryFree = false;
				passingEntrance.await(1, TimeUnit.SECONDS);
				passingEntrance.signal();
				secondEntryFree = true;	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}*/



	public void passFirstEntrance() throws InterruptedException {
		synchronized(this) {
			firstEntryFree = false;
			wait(1000);
			firstEntryFree = true;
		}
	}

	public void passSecondEntrance() throws InterruptedException {
		synchronized(this) {
			secondEntryFree = false;
			wait(1000);
			secondEntryFree = true;
		}
	}

	public void waitIn() throws InterruptedException {
		int randTimeIn = 0;
		randTimeIn =  1000 + (int)(Math.random() * 4001);						// czeka 1-5s
		Thread.sleep(randTimeIn);
	}

	public void waitOut() throws InterruptedException {
		int randTimeOut = 0;
		randTimeOut = 5000 + (int)(Math.random() * 5001);						// czeka 5-10s
		Thread.sleep(randTimeOut);
	}

	public boolean checkFirstEntrance() {
		return firstEntryFree;
	}

	public boolean checkSecondEntrance() {
		return secondEntryFree;
	}

	public ExecutorService getExecut() {
		return execut;
	}
}