import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
	public static int counter = 0;
    public Lock lock;
    public Condition[] self;
    public State[] state;


    public DiningPhilosophers() {

        lock = new ReentrantLock();
        state = new State[5];
        self = new Condition[5];
        int i=0;
        while (i<5) {
            state[i] = State.THINKING;
            System.out.println(message(i));
            Philosophers.sleeping();
            self[i] = lock.newCondition();
            i++;
        }

    }

    public void takeChopsticks(int i) {
        lock.lock();
        state[i]=State.HUNGRY;
        test(i);

        if (state[i] != State.EATING) {
            try {
                self[i].await();
            } catch (InterruptedException e){
                System.out.println("Philosopher "+i+" is waiting to eat.");
            }
        }
        lock.unlock();
    }

    public void returnChopsticks(int i) {
        lock.lock();
        state[i] = State.THINKING;
        System.out.println(message(i));
        Philosophers.sleeping();

        test((i + 4) % 5);
        test((i + 1) % 5);
        System.out.println("\n");

        lock.unlock();
    }

    private void test(int i) {
        if ((state[((i + 4) % 5)] != State.EATING)
            && (state[i] == State.HUNGRY)
                && (state[((i + 1) % 5)] != State.EATING)){
                    state[i] = State.EATING;
                    System.out.print(message(i));
                    Philosophers.sleeping();
                    self[i].signal();
                }

    }

    public String message(int i) {
        StringBuilder msg = new StringBuilder("Philosopher ");
        if(state[i]==State.THINKING) {
            if(counter>5) {
                msg.append(i+" released its left and right chopsticks.\n");
                msg.append("Philosopher "+i+" is thinking");
            }
            if(counter<=5) msg.append(i+" is thinking");
        }
        if(state[i]==State.EATING) {
            msg.append(i+" acquired its left and right chopsticks.\n");
            msg.append("Philosopher "+i+" is eating.\n");
        }
        counter++;
        return msg.toString();
    }


}
