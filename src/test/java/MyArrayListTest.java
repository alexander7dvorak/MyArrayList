package test.java;

import com.company.MyArrayList;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Tests for MyArrayList class {@link com.company.MyArrayList}
 *
 * @author Aleksandr Dvorak
 */
public class MyArrayListTest {
    static MyArrayList<Integer> testIntegers = new MyArrayList<>(100);

    static {
        testIntegers.add(1);
        testIntegers.add(5);
        testIntegers.add(8);
        testIntegers.add(4);
        testIntegers.add(3);
        testIntegers.add(7);
        testIntegers.add(6);
    }

    @Test
    public void add() {
        MyArrayList<String> mal1 = new MyArrayList<>(10);
        mal1.add("one");
        assertArrayEquals(new String[]{"one"}, mal1.toArray());
        MyArrayList<Integer> mal2 = new MyArrayList<>(15);
        mal2.add(2);
        mal2.add(0, 1);
        assertArrayEquals(new Integer[]{1, 2}, mal2.toArray());
    }

    @Test
    public void constuctors() {
        MyArrayList<String> example1 = new MyArrayList<>();
        MyArrayList<String> example2 = new MyArrayList<>(100);
        java.util.ArrayList<Integer> al1 = new java.util.ArrayList<>();
        al1.add(1);
        MyArrayList<Integer> example3 = new MyArrayList<>(al1);
    }

    @Test
    public void addAll() {
        MyArrayList<Integer> mal1 = new MyArrayList<>(10);
        java.util.ArrayList<Integer> al1 = new java.util.ArrayList<>();
        al1.add(1);
        al1.add(2);
        mal1.addAll(al1);
        assertArrayEquals(new Integer[]{1, 2}, mal1.toArray());

        MyArrayList<Integer> mal2 = new MyArrayList<>(10);
        MyArrayList<Integer> mal3 = new MyArrayList<>(10);
        mal3.add(1);
        mal3.add(2);
        mal2.addAll(mal3);
        assertArrayEquals(new Integer[]{1, 2}, mal2.toArray());

        MyArrayList<Integer> mal4 = new MyArrayList<>(10);
        mal4.add(5);
        java.util.ArrayList<Integer> al2 = new java.util.ArrayList<>();
        al2.add(1);
        al2.add(2);
        mal4.addAll(0, al2);
        assertArrayEquals(new Integer[]{1, 2, 5}, mal4.toArray());

        MyArrayList<Integer> mal5 = new MyArrayList<>(10);
        mal5.add(5);
        MyArrayList<Integer> mal6 = new MyArrayList<>(10);
        mal6.add(1);
        mal6.add(2);
        mal5.addAll(0, mal6);
        assertArrayEquals(new Integer[]{1, 2, 5}, mal5.toArray());
    }

    @Test
    public void clear() {
        MyArrayList<String> exmp = new MyArrayList<>();
        exmp.add("one");
        exmp.clear();
        assertEquals(exmp.isEmpty(), true);
    }

    @Test
    public void testClone() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        MyArrayList<String> exmpClone = (MyArrayList<String>) exmp.clone();
        assertEquals(exmp, exmpClone);
        assertEquals(true, exmp.equals(exmpClone));
        assertEquals(false, exmp.equals(testIntegers));
        exmp.hashCode();
    }

    @Test
    public void contains() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        assertEquals(true, exmp.contains("one"));
        assertEquals(false, exmp.contains("two"));
        exmp.add("two");
        assertEquals(true, exmp.contains("two"));
    }

    @Test
    public void ensureCapacity() {
        MyArrayList<Integer> mal1 = new MyArrayList<>(10);
        mal1.ensureCapacity(11);
        assertThrows(IllegalArgumentException.class, () -> {
            mal1.ensureCapacity(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            mal1.ensureCapacity(0);
        });
    }

    @Test
    public void get() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        assertEquals("one", exmp.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            exmp.get(2);
        });
    }

    @Test
    public void indexOf() {
        assertEquals(0, testIntegers.indexOf(1));
    }

    @Test
    public void isEmpty() {
        MyArrayList<Integer> emp = new MyArrayList<Integer>(10);
        assertEquals(true, emp.isEmpty());
        emp.add(1);
        assertEquals(false, emp.isEmpty());
        emp.remove(0);
        assertEquals(true, emp.isEmpty());
    }

    @Test
    public void iterator() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        Iterator<String> iter = exmp.iterator();
        assertEquals("one", iter.next());
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    public void lastIndexOf() {
        assertEquals(0, testIntegers.lastIndexOf(1));
    }

    @Test
    public void remove() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        exmp.add("two");
        assertEquals(true, exmp.remove("one"));
        exmp.remove("two");
        assertEquals(true, exmp.isEmpty());
        assertEquals(false, exmp.remove("one"));
        exmp.add("one");
        assertEquals("one", exmp.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            exmp.remove(-1);
        });
    }

    @Test
    public void removeAll() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        exmp.add("two");
        java.util.ArrayList<String> al2 = new java.util.ArrayList<>();
        al2.add("one");
        al2.add("two");
        exmp.removeAll(al2);
        assertEquals(true, exmp.isEmpty());
        MyArrayList<String> exmp1 = new MyArrayList<>(10);
        exmp1.add("three");
        exmp1.add("four");
        assertEquals(false, exmp.removeAll(exmp1));
        exmp.add("three");
        assertEquals(true, exmp.removeAll(exmp1));
    }

    @Test
    public void set() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        exmp.add("two");
        exmp.set(0, "three");
        assertEquals("three", exmp.get(0));
    }

    @Test
    public void size() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        assertEquals(0, exmp.size());
        exmp.add("one");
        assertEquals(1, exmp.size());
        exmp.remove(0);
        assertEquals(0, exmp.size());
    }

    @Test
    public void sort() {
        Integer[] sorted = testIntegers.toArray(new Integer[0]);
        Arrays.sort(sorted);
        MyArrayList<Integer> mySorted = (MyArrayList<Integer>) testIntegers.clone();
        Comparator<Integer> c = Comparator.naturalOrder();
        mySorted.sort(c);
        Integer[] mySortedArr = mySorted.toArray(new Integer[0]);
        assertEquals(false, Arrays.equals(sorted, testIntegers.toArray()));
        assertEquals(true, Arrays.equals(sorted, mySortedArr));
        MyArrayList<Integer> mySortedQuick = (MyArrayList<Integer>) testIntegers.clone();
        mySortedQuick.quickSort(c);
        Integer[] myQuickSortedArr = mySortedQuick.toArray(new Integer[0]);
        assertEquals(true, Arrays.equals(sorted, myQuickSortedArr));
    }

    @Test
    public void toArray() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        assertArrayEquals(new String[]{"one"}, exmp.toArray());
    }

    @Test
    public void trimToSize() {
        MyArrayList<String> exmp = new MyArrayList<>(10);
        exmp.add("one");
        exmp.trimToSize();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            exmp.set(1, "two");
        });
    }
}