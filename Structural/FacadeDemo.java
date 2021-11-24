package Structural;

/*
# Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level  interface that makes the subsystem easier
to use. Wrap a complicated subsystem with a simpler interface.

1. Identify the desired unified interface for a set of subsystems 
2. Design a"wrapper" class that can encapsulate the use of the subsystems 
3. The client uses (is coupled to) the Facade
4. The facade/wrapper "maps" to the APIs of the subsystems

*/

/*
  ---------
  |client1|----\   
  ---------     \--------
                 |Facade|                   
  ---------     /--------           
  |client2|----/                
  ---------            
*/

// 1. Subsystem
class PointCartesian {
    private double x, y;

    public PointCartesian(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public String toString() {
        return ("(" + x + "," + y + ")");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

// 1. Subsystem
class PointPolar {
    private double radius, angle;

    public PointPolar(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public void rotate(int angle) {
        this.angle += angle % 360;
    }

    public String toString() {
        return ("[" + radius + "@" + angle + "]");
    }

}

// 1. Desired interface : move(), rotate()

class Point {
    // 2. Design a "wrapper" class
    private PointCartesian pointCartesian;

    public Point(double x, double y) {
        pointCartesian = new PointCartesian(x, y);
    }

    public String toString() {
        return pointCartesian.toString();
    }

    // 4. Wrapper maps
    public void move(int x, int y) {
        pointCartesian.move(x, y);
    }

    public void rotate(int angle, Point obj) {
        double x = pointCartesian.getX() - obj.pointCartesian.getX();
        double y = pointCartesian.getY() - obj.pointCartesian.getY();

        PointPolar pointPolar = new PointPolar(Math.sqrt(x * x + y * y), Math.atan2(y, x) * 180 / Math.PI);
        // 4. Wrapper maps
        pointPolar.rotate(angle);
        System.out.println(" PointPolar is " + pointPolar);
        String str = pointPolar.toString();
        int i = str.indexOf('@');
        double r = Double.parseDouble(str.substring(1, i));
        double a = Double.parseDouble(str.substring(i + 1, str.length() - 1));

        pointCartesian = new PointCartesian(r * Math.cos(a * Math.PI / 180) + obj.pointCartesian.getX(),
                r * Math.sin(a * Math.PI / 180) + obj.pointCartesian.getY());
    }

}

class LineA {
    private Point o, e;

    public LineA(Point ori, Point end) {
        o = ori;
        e = end;
    }

    public void move(int x, int y) {
        o.move(x, y);
        e.move(x, y);
    }

    public void rotate(int angle) {
        e.rotate(angle, o);
    }

    public String toString() {
        return "origin is " + o + ", end is " + e;
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        // 3. Client uses the Facade
        LineA lineA = new LineA(new Point(2, 4), new Point(5, 7));
        lineA.move(-2, -4);
        System.out.println("after move:  " + lineA);
        lineA.rotate(45);
        System.out.println("after rotate: " + lineA);
        LineA lineB = new LineA(new Point(2, 1), new Point(2.866, 1.5));
        lineB.rotate(30);
        System.out.println("30 degrees to 60 degrees: " + lineB);
    }
}
