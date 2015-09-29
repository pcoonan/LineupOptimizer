public class MyRunnable implements Runnable {
  private final int curThread;
  private final int numThread;

  MyRunnable(int curThread, int numThread) {
    this.curThread = curThread;
    this.numThread = numThread;
  }

  @Override
  public void run() {
    long sum = 0;
    long NUMITERATIONS = 1000000000;
    final long START = (NUMITERATIONS / numThread) * curThread;
    final long STOP = ((NUMITERATIONS / numThread) * (curThread + 1)) - 1;
    
    for(long i = START; i < STOP; i++){
    	for(int k = 0; k < 100; k++){
    		sum = sum + i;            //runs 100 * 1000000000 times
    	}
    }
    
    System.out.println("Thread starting at: " + START + ", ending at: " + STOP);
  }
} 