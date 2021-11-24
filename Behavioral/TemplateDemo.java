package Behavioral;
/* Define the skeleton of an algorithm in an operation, deferring some steps to client subclasses. 
Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.
Base class declares algorithm 'placeholders', and derived classes implement the placeholders.
*/

// Template pattern defines steps of the algorithm and allows subclass to provide the implementation for one or more steps.
abstract class Generalization {
    // 1. Standardize the skeleton of an algorithm in a "template" method
    void findSolution() {
        stepOne();
        stepTwo();
        stepThr();
        stepFor();
    }

    // 2. Common implementations of individual steps are defined in base class
    private void stepOne() {
        System.out.println("Generalization.stepOne");
    }

    // 3. Steps requiring peculiar implementations are "placeholders" in the base
    // class
    abstract void stepTwo();

    abstract void stepThr();

    void stepFor() {
        System.out.println("Generalization.stepFor");
    }
}

abstract class Specialization extends Generalization {
    // 4. Derived classes can override placeholder methods
    // 1. Standardize the skeleton of an algorithm in a "template" method
    protected void stepThr() {
        step3_1();
        step3_2();
        step3_3();
    }

    // 2. Common implementations of individual steps are defined in base class
    private void step3_1() {
        System.out.println("Specialization.step3_1");
    }

    // 3. Steps requiring peculiar implementations are "placeholders" in the base
    // class
    abstract protected void step3_2();

    private void step3_3() {
        System.out.println("Specialization.step3_3");
    }
}

class Realization extends Specialization {
    // 4. Derived classes can override placeholder methods
    protected void stepTwo() {
        System.out.println("Realization.stepTwo");
    }

    protected void step3_2() {
        System.out.println("Realization.step3_2");
    }

    // 5. Derived classes can override implemented methods
    // 6. Derived classes can override and "call back to" base class methods
    protected void stepFor() {
        System.out.println("Realization.stepFor");
        super.stepFor();
    }
}

public class TemplateDemo {
    public static void main(String[] args) {
        Generalization algorithm = new Realization();
        algorithm.findSolution();
    }
}

// PRACTICE
/*
 * abstract class Juicer {
 * 
 * public final void makeJuice() { pourWater(); blend(); addSugar();
 * refegerate(); serve();
 * 
 * }
 * 
 * public void pourWater() { System.out.println("pouring water"); }
 * 
 * public abstract void blend();
 * 
 * public abstract void addSugar();
 * 
 * public abstract void refegerate();
 * 
 * public void serve() { System.out.println("Serving to the customers"); }
 * 
 * }
 * 
 * class OrangeJuiceMaker extends Juicer {
 * 
 * @Override public void blend() { System.out.println("Blending Orange."); }
 * 
 * @Override public void addSugar() {
 * System.out.println("Add 3 cubes of sugar"); }
 * 
 * @Override public void refegerate() {
 * System.out.println("Refegerate for 30 minutes"); }
 * 
 * }
 * 
 * class MangoJuiceMaker extends Juicer {
 * 
 * @Override public void blend() { System.out.println("Blending Mango."); }
 * 
 * @Override public void addSugar() {
 * System.out.println("Add 2 cubes of sugar"); }
 * 
 * @Override public void refegerate() {
 * System.out.println("Refegerate for 20 minutes"); }
 * 
 * }
 * 
 * public class Practice { public static void main(String[] args) { Juicer
 * orangeJuiceMaker = new OrangeJuiceMaker(); Juicer mangoJuiceMaker = new
 * MangoJuiceMaker();
 * 
 * orangeJuiceMaker.makeJuice(); mangoJuiceMaker.makeJuice(); } }
 * 
 */