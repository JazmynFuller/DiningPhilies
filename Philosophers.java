
public class Philosophers implements Runnable {
	
	    public static int sleepDuration = 5;
	    public State state;
	    public DiningPhilosophers philDining;
	    public int philNumber;

	    public Philosophers (int i, DiningPhilosophers j) {
	        philNumber = i;
	        philDining = j;
	    }

	    public void run() {
	        while (true) {
	            state = State.THINKING;
	            sleeping();
	            state = State.HUNGRY;
	            philDining.takeChopsticks(philNumber);
	            eat();
	            philDining.returnChopsticks(philNumber);
	        }
	    }

	    private void eat() {
	        sleeping();
	    }

	    static void sleeping() {
	        int sleepTime = (int) (sleepDuration * Math.random());
	        try {
	            Thread.sleep(sleepTime*1000);
	        } catch (InterruptedException e) {
	            System.out.print("Didn't sleep");
	        }
	    }
	
}
