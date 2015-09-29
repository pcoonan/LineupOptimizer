//import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public class Threads{
	
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		int numCores = Runtime.getRuntime().availableProcessors();
//		int numCores = 1;                                          //use this to see difference in performance
		
		System.out.println("Number of Cores: " + numCores);
		System.out.println();
		
	    ExecutorService executor = Executors.newFixedThreadPool(numCores);
	    
	    for (int i = 0; i < numCores; i++) {
	        Runnable worker = new MyRunnable(i,numCores);
	        executor.execute(worker);
	    }
	    // This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    
	    // Wait until all threads are finished
	    try {
	        executor.awaitTermination(100, TimeUnit.SECONDS);
	    } catch (InterruptedException ie) {
	        // (Re-)Cancel if current thread also interrupted
	        executor.shutdownNow();
	        // Preserve interrupt status
	        Thread.currentThread().interrupt();
	    }
	      
	    System.out.println();
	    System.out.println("Finished all threads");
	    
	    long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println();
	    System.out.println("Runtime: " + totalTime + " Milliseconds"); //tells how long program took
	}
}