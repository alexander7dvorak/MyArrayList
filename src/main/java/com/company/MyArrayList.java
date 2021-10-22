package com.company;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Custom ArrayList implementation
 *
 * @param <T> Type of elements
 * @author Aleksandr Dvorak
 */
public class MyArrayList<T> implements ArrayList<T> {
    /**
     * Iterator of MyArrayList
     *
     * @param <T> Type of MyArrayList elements
     */
    private class MyArrayListIterator<T> implements Iterator<T> {
        /**
         * Size of MyArrayList
         */
        int size = size();
        /**
         * Index on which iterator points
         */
        int currentPointer;

        MyArrayListIterator() {
            currentPointer = 0;
        }

        /**
         * Checks if there's an element by the next index
         *
         * @return true - if there's an element, false - if not
         */
        public boolean hasNext() {
            return (currentPointer < size);
        }

        /**
         * Get next element of MyArrayList
         *
         * @return Next element
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) get(currentPointer++);
        }

    }

    /**
     * Array of generic type for containment of elements
     */
    private T[] elementData;
    /**
     * Current number of actual elements inside array
     */
    private int size = 0;

    public MyArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
    }

    public MyArrayList() {
        elementData = (T[]) new Object[10];
    }

    public MyArrayList(MyArrayList that) {
        elementData = (T[]) new Object[that.size];
        addAll(that);
    }

    public MyArrayList(Collection<T> that) {
        elementData = (T[]) new Object[that.size()];
        addAll(that);
    }

    /**
     * Adds element of generic type to the end of ArrayList
     *
     * @param e Element to be added
     * @return Always true, as adding similar objects is enabled in ArrayList
     */
    public boolean add(T e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    /**
     * Adds element of generic type to ArrayList by index
     *
     * @param index Index where element should be added
     * @param e     Element to be added
     */
    public void add(int index, T e) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        ++size;
    }

    /**
     * Adds all elements of collection to the ArrayList
     *
     * @param c Collection from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(Collection<? extends T> c) {
        ensureCapacity(size + c.size());
        Iterator iter = c.iterator();
        for (int i = 0; i < c.size(); ++i)
            elementData[size++] = (T) iter.next();
        return true;
    }

    /**
     * Adds all elements of some ArrayList to this ArrayList
     *
     * @param c ArrayList from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(ArrayList<? extends T> c) {
        ensureCapacity(size + c.size());
        for (int i = 0; i < c.size(); ++i)
            elementData[size++] = c.get(i);
        return true;
    }

    /**
     * Adds all elements of collection to this ArrayList
     *
     * @param index Index to which elements should be added in ArrayList
     * @param c     Collection from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(int index, Collection<? extends T> c) {
        ensureCapacity(size + c.size());
        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        Iterator iter = c.iterator();
        for (int i = index; i < index + c.size(); ++i)
            elementData[i] = (T) iter.next();
        size += c.size();
        return true;
    }

    /**
     * Adds all elements of some ArrayList to this ArrayList
     *
     * @param index Index to which elements should be added in ArrayList
     * @param c     ArrayList from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(int index, ArrayList<? extends T> c) {
        ensureCapacity(size + c.size());
        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        for (int i = index, j = 0; j < c.size(); ++i, ++j)
            elementData[i] = c.get(j);
        size += c.size();
        return true;
    }

    /**
     * Clears ArrayList
     */
    public void clear() {
        for (int i = 0; i < size; ++i)
            elementData[i] = null;
        size = 0;
    }

    /**
     * Clones this ArrayList instance
     *
     * @return Clone
     */
    public Object clone() {
        return new MyArrayList<T>(this);
    }

    /**
     * Checks if ArrayList contains element e of type T
     *
     * @param e Element to be checked
     * @return true - if ArrayList contains element, false - if not
     */
    public boolean contains(T e) {
        for (int i = 0; i < size; ++i)
            if (elementData[i].equals(e))
                return true;
        return false;
    }

    /**
     * Enlarges capacity of ArrayList if neeeded
     *
     * @param minCapacity Minimal capacity needed
     */
    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity < 1) {
            throw new IllegalArgumentException();
        } else if (oldCapacity < minCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            T[] oldData = (T[]) new Object[oldCapacity];
            for (int i = 0; i < oldCapacity; ++i)
                oldData[i] = elementData[i];
            elementData = (T[]) new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }
    }

    /**
     * Gets element of ArrayList by index
     *
     * @param index Index
     * @return Element of generic type
     */
    public T get(int index) {
        if (index >= 0 && index < size)
            return elementData[index];
        else throw new IndexOutOfBoundsException();
    }

    /**
     * Searches first occurrence of some object in ArrayList, if it exists
     *
     * @param o Object to be searched
     * @return Idex of object in ArrayList, or -1, if it doesn't exist in ArrayList
     */
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Checks, weather ArrayList is empty or not
     *
     * @return true - if ArrayList is empty, false - if not
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Get iterator of ArrayList
     *
     * @return Iterator of ArrayList
     */
    public Iterator<T> iterator() {
        return new MyArrayListIterator<>();
    }

    /**
     * Searches last occurrence of some object in ArrayList, if it exists
     *
     * @param o Object to be searched
     * @return Idex of object in ArrayList, or -1, if it doesn't exist in ArrayList
     */
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = size - 1; i > -1; --i) {
            if (elementData[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Removes element from ArrayList by index
     *
     * @param index Index of some element
     * @return Removed element
     */
    public T remove(int index) {
        if (index >= 0 && index < size) {
            int numMoved = size - index - 1;
            T res = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--size] = null;
            return res;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * Removes object from ArrayList
     *
     * @param o Object to be removed
     * @return true - if object was removed, false - if wasn't
     */
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all elements of some collection from ArrayList
     *
     * @param c Collection from which elements are compared with elements from ArrayList
     * @return True - if at least one element was removed, false - if none of elements were removed
     */
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            if (remove(iter.next())) res = true;
        }
        return res;
    }

    /**
     * Removes all elements of some ArrayList from ArrayList
     *
     * @param c ArrayList from which elements are compared with elements from this ArrayList
     * @return True - if at least one element was removed, false - if none of elements were removed
     */
    public boolean removeAll(MyArrayList<?> c) {
        boolean res = false;
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            if (remove(iter.next())) res = true;
        }
        return res;
    }

    /**
     * Sets element of ArrayList by index
     *
     * @param index Index
     * @return Element of generic type
     */
    public T set(int index, T e) {
        if (index >= 0 && index < size) {
            elementData[index] = e;
        } else throw new IndexOutOfBoundsException();
        return e;
    }

    /**
     * Get size of ArrayList
     *
     * @return Int size of ArrayList
     */
    public int size() {
        return size;
    }


    /**
     * Sorts array of elements inside ArrayList using merge sort
     *
     * @param c Comparator for comparison of objects
     */
    public void sort(Comparator<? super T> c) {
        mergeSort(elementData, 0, size - 1, c);
    }

    /**
     * Sorts array of elements inside ArrayList using quick sort
     *
     * @param c Comparator for comparison of objects
     */
    public void quickSort(Comparator<? super T> c) {
        quickSort(elementData, c);
    }

    /**
     * Utility method of quickSort
     *
     * @param array Array to be sorted
     * @param c     Comparator for comparison of objects
     */
    private void quickSort(T[] array, Comparator<? super T> c) {
        qSort(array, 0, array.length - 1, c);
    }

    /**
     * Utility method of quickSort
     *
     * @param array Array to be sorted
     * @param left  Left index
     * @param right Right index
     * @param c     Comparator for comparison of objects
     */
    private void qSort(T[] array, int left, int right, Comparator<? super T> c) {
        int ll = left;
        int rr = right;
        if (rr > ll) {
            T pivot = array[(ll + rr) / 2];
            while (ll <= rr) {
                while (ll < right && c.compare(array[ll], pivot) < 0) {
                    ll += 1;
                }
                while (rr > left && c.compare(array[rr], pivot) > 0) {
                    rr -= 1;
                }
                if (ll <= rr) {
                    swap(array, ll, rr);
                    ll += 1;
                    rr -= 1;
                }
            }
            if (left < rr) {
                qSort(array, left, rr, c);
            }
            if (ll < right) {
                qSort(array, ll, right, c);
            }
        }
    }

    /**
     * Swaps two elements of array
     *
     * @param arr Array
     * @param l   First index
     * @param r   Second index
     */
    void swap(T[] arr, int l, int r) {
        T temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    /**
     * Utility method of sort to implement merge sorting
     *
     * @param a    Array to be sorted
     * @param from Starting index
     * @param to   Finishing index
     * @param c    Comparator for comparison of objects
     */
    private void mergeSort(T[] a, int from, int to, Comparator<? super T> c) {
        if (from == to)
            return;
        int mid = (from + to) / 2;
        mergeSort(a, from, mid, c);
        mergeSort(a, mid + 1, to, c);
        merge(a, from, mid, to, c);
    }

    /**
     * Utility method of mergeSort
     *
     * @param a    Array to be sorted
     * @param from First index
     * @param mid  Second index
     * @param to   Third index
     * @param c    Comparator for comparison of objects
     */
    private void merge(T[] a, int from, int mid, int to, Comparator<? super T> c) {
        int n = to - from + 1;
        Object[] values = new Object[n];
        int fromValue = from;
        int middleValue = mid + 1;
        int index = 0;
        while (fromValue <= mid && middleValue <= to) {
            if (c.compare(a[fromValue], a[middleValue]) < 0) {
                values[index] = a[fromValue];
                fromValue++;
            } else {
                values[index] = a[middleValue];
                middleValue++;
            }
            index++;
        }
        while (fromValue <= mid) {
            values[index] = a[fromValue];
            fromValue++;
            index++;
        }
        while (middleValue <= to) {
            values[index] = a[middleValue];
            middleValue++;
            index++;
        }
        for (index = 0; index < n; index++)
            a[from + index] = (T) values[index];
    }

    /**
     * Gets array of elements of ArrayList with the same size and type
     *
     * @return Array of elements of ArrayList
     */
    public Object[] toArray() {
        Object[] resData = new Object[size];
        System.arraycopy(elementData, 0, resData, 0, size);
        return resData;
    }

    /**
     * Gets array of elements of ArrayList with the same size and type
     *
     * @param a   Array of generic tyep for casting
     * @param <T> Generic Type
     * @return Array of elements of ArrayList
     */
    public <T> T[] toArray(T[] a) {
        T[] resData = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        System.arraycopy(elementData, 0, resData, 0, size);
        return resData;
    }

    /**
     * Shrinks capacity of ArrayList to it's size
     */
    public void trimToSize() {
        T[] oldData = (T[]) new Object[size];
        for (int i = 0; i < size; ++i)
            oldData[i] = elementData[i];
        elementData = (T[]) new Object[size];
        System.arraycopy(oldData, 0, elementData, 0, size);
    }

    /**
     * Overriden equals method
     *
     * @param o Object to be checked
     * @return true - if objects are equal, false - if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<T> that = (MyArrayList<T>) o;
        if (size == that.size) {
            for (int i = 0; i < size; ++i) {
                if (elementData[i] != that.elementData[i]) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Overriden hashCode() method
     *
     * @return Hash of this MyArrayList instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(elementData, size);
    }
}