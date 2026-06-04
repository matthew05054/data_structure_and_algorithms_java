package threadsDemo;

class AutoSave extends Thread{
	public void run() {
		System.out.println(Thread.currentThread().getName()
				+": " + Thread.currentThread().getPriority());
		
		Thread.currentThread().setPriority(MAX_PRIORITY);
		
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(3000);
				System.out.println("Saving code!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

public class CodeEditor {

	public static void main(String[] args) throws InterruptedException {
		AutoSave autoSave = new AutoSave();
		autoSave.start();
		
		for(int i=0;i<10;i++) {
			Thread.sleep(2000);
			System.out.println("Typing code...");
		}
		
		
	}

}
