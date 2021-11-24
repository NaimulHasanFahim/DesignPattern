package Structural;

// Attach additional responsibilities to an object dynamically.

// Decorators provide a flexible alternative to subclassing for extending functionality.
// Client-specified embellishment of a core object by recursively wrapping it.

/*
class A {

    public void doIt() {
        System.out.print(' ');
        System.out.print('A');
    }
}

class AwithX extends A {

    public void doIt() {
        super.doIt();
        doX();
    }

    public void doX() {
        System.out.print('X');
        // System.out.print(' ');
    }
}

class AwithY extends A {

    public void doIt() {
        super.doIt();
        doY();
    }

    public void doY() {
        System.out.print('Y');
        // System.out.print(' ');
    }
}

class AwithZ extends A {

    public void doIt() {
        super.doIt();
        doZ();
    }

    public void doZ() {
        System.out.print('Z');
        // System.out.print(' ');
    }
}

class AwithXY extends AwithX {
    private AwithY obj = new AwithY();

    public void doIt() {
        super.doIt();
        obj.doY();
    }
}

public class DecoratorDemo {

    public static void main(String[] args) {
        AwithX temp1 = new AwithX();
        AwithY temp2 = new AwithY();
        AwithZ temp3 = new AwithZ();
        AwithXY temp4 = new AwithXY();

        temp1.doIt();
        temp2.doIt();
        temp3.doIt();
        temp4.doIt();
    }
}
*/
// BUT we can't attach something in run time

// 1. "lowest common denominator"
interface I {
    void doIt();
}

// 2. Second level base class with "isa" relationship
abstract class decorator implements I {
    // 4. "has a" relationship
    private I core;

    decorator(I inner) {
        this.core = inner;
    }

    // 5. Delegation
    @Override
    public void doIt() {
        core.doIt();
    }

}

// 3. "Core" class with "is a" relationship
class A implements I {

    @Override
    public void doIt() {
        System.out.print('A');

    }

}

class B implements I {

    @Override
    public void doIt() {
        System.out.print('B');
    }

}

// 6. Optional embellishment
class X extends decorator {

    public X(I inner) {
        super(inner);

    }

    public void doIt() {
        super.doIt(); // 7. Delegate to base class and add extra stuff
        doX();
    }

    public void doX() {
        System.out.print('X');
    }

}

// 6. Optional embellishment
class Y extends decorator {

    public Y(I inner) {
        super(inner);

    }

    public void doIt() {
        super.doIt(); // 7. Delegate to base class and add extra stuff
        doY();
    }

    public void doY() {
        System.out.print('Y');
    }

}

// 6. Optional embellishment
class Z extends decorator {

    public Z(I inner) {
        super(inner);

    }

    public void doIt() {
        super.doIt(); // 7. Delegate to base class and add extra stuff
        doZ();
    }

    public void doZ() {
        System.out.print('Z');
    }

}

public class DecoratorDemo {
    public static void main(String[] args) {
        // 8. Client has the responsibility to compose desired configurations
        I temp1 = new Z(new A());
        I temp2 = new X(new Y(new A()));
        I temp3 = new Z(new Y(new X(new B())));
        I temp4 = new A();

        temp1.doIt();
        System.out.print(' ');
        temp2.doIt();
        System.out.print(' ');
        temp3.doIt();
        System.out.print(' ');
        temp4.doIt();
    }
}
