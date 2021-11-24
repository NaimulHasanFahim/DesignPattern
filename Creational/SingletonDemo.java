package Creational;

// Ensure a class has only one instance, and provide a global point of access to it.Encapsulated"just-in-time initialization"or  "initialization on first use".
// If ownership of the single instance, when and how initialization occurs, and global access are not issues, Singleton is not sufficiently interesting.

class Singleton {
    private static Singleton instance;
    private int id;
    private String name;
    private double marks;

    private Singleton() {
        this.id = 2017331059;
        this.name = "Fahim";
        this.marks = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Singleton getInstance() {
        System.out.println("Hi in the static method which can only access static variable.");
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}

public class SingletonDemo {

    public static void main(String[] args) {
        Singleton temp = Singleton.getInstance();
        System.out.println(temp.getId() + " " + temp.getName());
        temp.setId(2017331089);
        temp.setName("Sabbir Irfan");

        Singleton temp1 = Singleton.getInstance();
        System.out.println(temp1.getId() + " " + temp1.getName());

    }
}
