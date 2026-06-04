package threadsDemo;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// DateTimeFormatter formatter = DateTimeFormatter.ofPatter("HH:mm:sss");


class AutoSave extends Thread{

	private static final DateTimeFormatter formatter =
	DateTimeFormatter.ofPattern("HH:mm:ss");
	public void run() {
		System.out.println(Thread.currentThread().getName()
				+": " + Thread.currentThread().getPriority());

		// Date date = new Date();
		// time_ = date.now();
		
		Thread.currentThread().setPriority(MIN_PRIORITY);
		System.out.println(Thread.currentThread().getPriority());
		
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(3000);
				System.out.println(LocalTime.now().format(formatter)  + " Saving code!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

public class CodeEditor {


	private static final DateTimeFormatter formatter =
	DateTimeFormatter.ofPattern("HH:mm:ss");
	public static void main(String[] args) throws InterruptedException {
		AutoSave autoSave = new AutoSave();
		autoSave.start();
		
		for(int i=0;i<10;i++) {
			Thread.sleep(2000);
			System.out.println(LocalTime.now().format(formatter) + " Typing code...");
		}
		
		
	}

}
