package Creational;

import java.util.HashMap;
import java.util.Map;
// import java.io.*;

interface Person {

    Person getClone();

    String getName();
}

class Fahim implements Person {
    private final String name = "N.H.Fahim Shah";

    @Override
    public Person getClone() {
        return new Fahim();
    }

    @Override
    public String getName() {
        return name;
    }

}

class Nowshad implements Person {
    private final String name = "Jahedul Alam Nowshad";

    @Override
    public Person getClone() {
        return new Nowshad();
    }

    @Override
    public String getName() {
        return name;
    }

}

class Sabbir implements Person {
    private final String name = "Sabbir Irfan Chy";

    @Override
    public Person getClone() {
        return new Sabbir();
    }

    @Override
    public String getName() {
        return name;
    }

}

class Factory {
    private static final Map<String, Person> prototypes = new HashMap<>();

    static {
        prototypes.put("Fahim", new Fahim());
        prototypes.put("Nowshad", new Nowshad());
        prototypes.put("Sabbir", new Sabbir());
        System.out.println(prototypes.size());
    }

    public static Person getPrototype(String name) {
        try {
            return prototypes.get(name).getClone();
        } catch (Exception ex) {
            System.out.println("Prototype with name: " + name + ", doesn't exist");
            return null;
        }
    }

}

public class ProtoType2 {
    public static void main(String[] args) {
        if (args.length > 0) {
            for (String type : args) {
                Person prototype = Factory.getPrototype(type);
                if (prototype != null) {
                    System.out.println(prototype);
                }
            }
        } else {
            System.out.println("Run again with arguments of command string ");
        }

    }
}
