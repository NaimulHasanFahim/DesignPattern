package Behavioral;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Observer {
    protected Subject subject; // 3. The Subject is coupled only to the Observer base class.

    public abstract void update();
}

// 1. Model the"independent" functionality with a"subject"abstraction.
class Subject {
    private ArrayList<Observer> allobserver = new ArrayList<>();
    private int state;

    void addObserver(Observer o) {
        allobserver.add(o);
    }

    public int getState() {
        return state;
    }

    public void setState(int val) {
        this.state = val;
        execute(); // 5. The Subject broadcasts events to all registered Observers.
    }

    // 5. The Subject broadcasts events to all registered Observers.
    private void execute() {
        for (Observer ob : allobserver) {
            ob.update();
        }
    }

}

// 2. Model the"dependent" functionality with"observer"hierarchy.
class binObserver extends Observer {

    public binObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this); // 4. Observers register themselves with the Subject.
    }

    @Override
    public void update() {
        System.out.print(" Binary: " + Integer.toBinaryString(subject.getState())); // 6. Observers "pull" the
                                                                                    // information they need from the
                                                                                    // Subject.
    }

}

// 2. Model the"dependent" functionality with"observer"hierarchy.
class octObserver extends Observer {

    public octObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this); // 4. Observers register themselves with the Subject.
    }

    @Override
    public void update() {
        System.out.print(" Octal: " + Integer.toOctalString(subject.getState())); // 6. Observers "pull" the information
                                                                                  // they need from the Subject.

    }

}

// 2. Model the"dependent" functionality with"observer"hierarchy.
class hexObserver extends Observer {

    public hexObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this); // 4. Observers register themselves with the Subject.
    }

    @Override
    public void update() {
        System.out.print(" Hexadecimal: " + Integer.toHexString(subject.getState())); // 6. Observers "pull" the
                                                                                      // information they need from the
                                                                                      // Subject.

    }

}

public class ObserverDemo {

    public static void main(String[] args) {
        Subject updater = new Subject();
        new hexObserver(updater); // 7. Client configures the number and type of Observers.
        new binObserver(updater); // 7. Client configures the number and type of Observers.
        new octObserver(updater); // 7. Client configures the number and type of Observers.

        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.print("\nEnter a number: ");
            updater.setState(scan.nextInt());
        }
        scan.close();
    }

}
