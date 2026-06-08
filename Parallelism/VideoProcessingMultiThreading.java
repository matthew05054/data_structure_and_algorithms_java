package threadsDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

class VideoProcessingTask  extends RecursiveAction {
    private final String videoFile;

    public VideoProcessingTask(String videoFile) {
        this.videoFile = videoFile;
    }

    @Override
    public void compute() {
        System.out.println("Processing video: " + videoFile + " on " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulating video processing time
        } catch (InterruptedException e) {
            System.out.println(videoFile + " processing interrupted.");
        }
        System.out.println("Completed: " + videoFile);
    }
}

public class VideoProcessingMultiThreading {

	public static void main(String[] args) throws InterruptedException {
        String[] videoFiles = {"video1.mp4", "video2.mp4", "video3.mp4", "video4.mp4"};

        ForkJoinPool pool = new ForkJoinPool();
                
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < videoFiles.length; i++) {
            pool.execute(new VideoProcessingTask(videoFiles[i]));
        }

        pool.shutdown();
        
        pool.awaitTermination(10, TimeUnit.SECONDS);

        long endTime = System.currentTimeMillis();
        System.out.println("All videos processed in parallel. Total time: " + (endTime - startTime) + " ms");
    }

}

