package threadsDemo;

import java.util.concurrent.RecursiveAction;

class VideoProcessingTask  extends RecursiveAction{
   private final String videoFile;

   public VideoProcessingTask(String videoFile) {
       this.videoFile = videoFile;
   }

   public void process() {
       System.out.println("Processing video: " + videoFile + " on " + Thread.currentThread().getName());
       try {
           Thread.sleep(2000); // Simulating video processing time
       } catch (InterruptedException e) {
           System.out.println(videoFile + " processing interrupted.");
       }
       System.out.println("Completed: " + videoFile);
   }

   @Override
   protected void compute() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'compute'");
   }
}

public class VideoProcessing {

	public static void main(String[] args) {
       String[] videoFiles = {"video1.mp4", "video2.mp4", "video3.mp4", "video4.mp4"};

       
       long startTime = System.currentTimeMillis();
       
       for (int i = 0; i < videoFiles.length; i++) {
           VideoProcessingTask videoProcessingTask = new VideoProcessingTask(videoFiles[i]);
           videoProcessingTask.process();
       }

       long endTime = System.currentTimeMillis();
       System.out.println("All videos processed in serial. Total time: " + (endTime - startTime) + " ms");
   }

}

