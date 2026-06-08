package threadsDemo;

import java.util.concurrent.locks.ReentrantLock;

class Account{
	int amount;
	ReentrantLock lock = new ReentrantLock();
	Account(int amount){
		this.amount = amount;
	}
	
	public  void decreement() {
		lock.lock();
		try {
			amount = amount - 1;
		}finally {
			lock.unlock();
		}	
	}
}

class AtmThreadA implements Runnable{
	Account account;
	public AtmThreadA(Account account) {
		this.account = account;
	}
	
	public void run() {
		for(int i=0;i<1000;i++) {
			account.decreement();
		}
	}
}

class AtmThreadB implements Runnable{
	Account account;
	public AtmThreadB(Account account) {
		this.account = account;
	}
	
	public void run() {
		for(int i=0;i<1000;i++) {
			account.decreement();
		}
	}
}

public class Bank {

	public static void main(String[] args) throws InterruptedException {
		Account account = new Account(2000);
		AtmThreadA atmThreadA = new AtmThreadA(account);
		AtmThreadB atmThreadB = new AtmThreadB(account);
		
		Thread threadA = new Thread(atmThreadA);
		Thread threadB = new Thread(atmThreadB);
		
		threadA.start();
		threadB.start();
		
		threadA.join();
		threadB.join();
		
		System.out.println("Final amount: " + account.amount);
		
	}

}
