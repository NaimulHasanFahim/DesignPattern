package Behavioral;

import java.util.ArrayList;

interface Command {
    void execute();
}

class DomesticEngineer implements Command {
    public void execute() {
        System.out.println("take out the trash");
    }
}

class Politician implements Command {
    public void execute() {
        System.out.println("take money from the rich, take votes from the poor");
    }
}

class Programmer implements Command {
    public void execute() {
        System.out.println("sell the bugs, charge extra for the fixes");
    }
}

public class CommandDemo {

    public static void main(String[] args) {
        ArrayList<Command> queue = new ArrayList<>();
        queue.add(new DomesticEngineer());
        queue.add(new Politician());
        queue.add(new Programmer());
        for (Object command : queue) {
            ((Command) command).execute();
        }
    }
}
