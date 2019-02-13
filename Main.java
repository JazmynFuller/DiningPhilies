
public class Main {
	 public static void main(String[] args) {
	        DiningPhilosophers readyToEat = new DiningPhilosophers();

	        for (int i=0; i<5; i++) {
	            Philosophers newPhilosopher = new Philosophers(i,readyToEat);
	            Thread dining = new Thread(newPhilosopher);
	            dining.start();
	        }

	        System.out.println("\n");

	    }
}
