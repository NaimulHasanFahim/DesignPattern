package Creational;

import java.util.ArrayList;

// Specify the kinds of objects to create using a prototypical instance,and create new objects by copying this prototype.
// Co-opt one instance of a class for use as a breeder of all future instances.The new operator considered harmful.

interface Base {
    Base clone();

    void execute();

    String getName();
}

class PrototypeModule {

    private static ArrayList<Base> prototypes = new ArrayList<>();

    public static void addPrototype(Base p) {
        prototypes.add(p);
    }

    public static Base createPrototype(String name) {

        for (Base p : prototypes) {
            // System.out.println(p.getName() + " " + name);
            if (p.getName().equals(name)) {
                return p;
            }
        }
        System.out.println(name + ": doesn't exist");
        return null;
    }

}

class TypeA implements Base {
    private String name = "Type_A";

    @Override
    public Base clone() {
        return new TypeA();
    }

    @Override
    public void execute() {
        System.out.println(name + ": does something");
    }

    @Override
    public String getName() {
        return name;
    }

}

class TypeB implements Base {
    private String name = "Type_B";

    @Override
    public Base clone() {

        return new TypeB();
    }

    @Override
    public void execute() {
        System.out.println(name + ": does something");
    }

    @Override
    public String getName() {
        return name;
    }

}

class ReleasePrototype implements Base {
    private String name = "ReleaseCandidate";

    @Override
    public Base clone() {
        return new ReleasePrototype();
    }

    @Override
    public void execute() {
        System.out.println(name + ": does real work");
    }

    @Override
    public String getName() {

        return name;
    }

}

public class Prototype {

    public static void main(String[] args) {
        initializePrototypes();
        if (args.length > 0) {

            ArrayList<Base> prototypes = new ArrayList<>();
            // 6. Client does not use "new"
            for (String name : args) {
                Base prototype = PrototypeModule.createPrototype(name);
                if (prototype != null) {
                    prototypes.add(prototype);
                }
            }
            for (Base p : prototypes) {
                p.execute();
            }
        } else {
            System.out.println("Run again with arguments of command string ");
        }

    }

    // 3. Populate the "registry"
    public static void initializePrototypes() {

        PrototypeModule.addPrototype(new TypeA());
        PrototypeModule.addPrototype(new TypeB());
        PrototypeModule.addPrototype(new ReleasePrototype());
    }
}
