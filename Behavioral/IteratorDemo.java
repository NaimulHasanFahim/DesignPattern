package Behavioral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

Need to "abstract" the traversal of wildly different data structures so that algorithms 
can be defined that are capable of interfacing with each transparently.

*/
// This expose the internal data to client
/*
class IntegerBox {
    private final List<Integer> list = new ArrayList<>();

    public void add(int num) {
        list.add(num);
    }

    public List getData() {
        return list;
    }

}


public static void main(String[] args) {
    IntegerBox integerBox = new IntegerBox();

    for (int i = 9; i >= 1; i--) {
        integerBox.add(i);
    }

    Collection integerList = integerBox.getData();
    for (Object anIntegerList : integerList) {
        System.out.print(anIntegerList + "  ");
    }
    System.out.println();
    integerList.clear();
    integerList = integerBox.getData();
    System.out.println("size of data is: " + integerList.size());
}
*/

class IntegerBox {
    private List<Integer> list = new ArrayList<>();

    class MyIterator {
        private IntegerBox Box;
        private Iterator<Integer> iterator;
        private int value;

        public MyIterator(IntegerBox integerBox) {
            this.Box = integerBox;
        }

        public void first() {
            iterator = Box.list.iterator();
            // System.out.println(iterator);
            next();
        }

        public void next() {
            try {
                value = (Integer) iterator.next();
            } catch (Exception ex) {
                value = -1;
            }
        }

        public boolean isDone() {
            return value == -1;
        }

        public int currentValue() {
            return value;
        }

    }

    public void add(int num) {
        list.add(num);
    }

    public IntegerBox.MyIterator getIterator() {
        return new MyIterator(this);
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        IntegerBox integerBox = new IntegerBox();
        for (int i = 9; i > 0; --i) {
            integerBox.add(i);
        }
        // getData() has been removed.
        // Client has to use Iterator.
        IntegerBox.MyIterator firstItr = integerBox.getIterator();
        IntegerBox.MyIterator secondItr = integerBox.getIterator();
        for (firstItr.first(); !firstItr.isDone(); firstItr.next()) {
            System.out.print(firstItr.currentValue() + "  ");
        }
        System.out.println();
        // Two simultaneous iterations
        for (firstItr.first(), secondItr.first(); !firstItr.isDone(); firstItr.next(), secondItr.next()) {
            System.out.print(firstItr.currentValue() + " " + secondItr.currentValue() + "  ");
        }

    }
}
