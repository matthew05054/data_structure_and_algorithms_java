package threadsDemo;

class SocialMedia implements Runnable{
	 public void run() {
		 System.out.println("Social Media app is starting");
		 for(int i=0;i<10;i++) {
			 System.out.println("Social Media app is running");
		 }
		 System.out.println("Social Media app ended");
	 }
}

class MusicPlayer implements Runnable{
	public void run() {
		System.out.println("Music Player is starting");
		 for(int i=0;i<10;i++) {
			 System.out.println("Music Player is running");
		 }
		 System.out.println("Music Player has ended");
	}
}

class FileDownloader implements Runnable{
	public void run() {
		System.out.println("File download is starting");
		 for(int i=0;i<10;i++) {
			 System.out.println("File download is running");
		 }
		 System.out.println("File download has ended");
	}
}

public class MobilePhone {

	public static void main(String[] args) {
		double startTime = System.nanoTime();
		
		SocialMedia socialMedia = new SocialMedia();
		MusicPlayer musicPlayer = new MusicPlayer();
		FileDownloader fileDownloader = new FileDownloader();
		
		Thread socialMediaThread = new Thread(socialMedia, "socialMediaThread");
		Thread musicPlayerThread = new Thread(musicPlayer, "musicPlayerThread");
		Thread fileDownloaderThread = new Thread(fileDownloader, "fileDownloaderThread");
		
		socialMediaThread.start();
		musicPlayerThread.start();
		fileDownloaderThread.start();
		
		double endTime = System.nanoTime();
		double timeTaken = endTime-startTime;
		System.out.println("Time taken for execution: " + timeTaken);
	}

}
