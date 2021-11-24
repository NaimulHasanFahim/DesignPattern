package Structural;

// Convert the interface of a class into another interface clients expect.
// Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.Wrap an existing class with a new interface.

interface Shape {
    void draw(int x1, int y1, int x2, int y2);
}

class Line {
    public void draw(int x1, int y1, int x2, int y2) {
        System.out.println("Line from point A(" + x1 + ";" + y1 + "), to point B(" + x2 + ";" + y2 + ")");
    }
}

class Rectangle {
    public void draw(int x, int y, int width, int height) {
        System.out.println("Rectangle with coordinate left-down point (" + x + ";" + y + "), width: " + width
                + ", height: " + height);
    }
}

class LineAdapter implements Shape {

    private Line line;

    public LineAdapter(Line line) {
        this.line = line;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        this.line.draw(x1, y1, x2, y2);
    }

}

class RectangleAdapter implements Shape {

    private Rectangle react;

    public RectangleAdapter(Rectangle rect) {
        this.react = rect;
    }

    @Override
    public void draw(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);

        react.draw(x, y, width, height);
    }

}

public class AdapterDemo {
    public static void main(String[] args) {

        Shape[] shapes = { new LineAdapter(new Line()), new RectangleAdapter(new Rectangle()) };
        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;

        for (Shape shape : shapes) {
            shape.draw(x1, y1, x2, y2);
        }
    }
}

/*
 * public class AdapterDemo { public static void main(String[] args) { Object[]
 * shapes = { new Line(), new Rectangle() }; int x1 = 10, y1 = 20; int x2 = 30,
 * y2 = 60; int width = 40, height = 40; for (Object shape : shapes) { if
 * (shape.getClass().getSimpleName().equals("Line")) { ((Line) shape).draw(x1,
 * y1, x2, y2); } else if (shape.getClass().getSimpleName().equals("Rectangle"))
 * { ((Rectangle) shape).draw(x2, y2, width, height); } } } }
 */

// PRACTICE
/*
 * 
 * class MyCar { private int hp; private String name;
 * 
 * public MyCar(int hp, String name) { this.hp = hp; this.name = name; }
 * 
 * public void steer() { System.out.println("Driving Car with steering."); } }
 * 
 * class Bike { private int hp; private String name;
 * 
 * public Bike(int hp, String name) { this.hp = hp; this.name = name; }
 * 
 * public void move() { System.out.println("Driving Bike with left right."); }
 * 
 * public int getHp() { return hp; }
 * 
 * public String getName() { return name; } }
 * 
 * class BikeToCar extends MyCar { Bike bike;
 * 
 * public BikeToCar(Bike bike) { super(bike.getHp(), bike.getName()); this.bike
 * = bike; }
 * 
 * public void steer() { bike.move(); }
 * 
 * }
 * 
 * public class Practice { public static void main(String[] args) { MyCar car1 =
 * new MyCar(1400, "Ferrari"); MyCar car2 = new BikeToCar(new Bike(250,
 * "Pulsar twin disks")); car1.steer(); car2.steer(); } }
 * 
 * 
 */