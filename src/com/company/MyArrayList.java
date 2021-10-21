package com.company;

import java.util.*;

public class MyArrayList<T> implements ArrayList<T> {
    private class MyArrayListIterator<T> implements Iterator<T> {
        int size = size();
        int currentPointer;

        MyArrayListIterator() {
            currentPointer = 0;
        }

        MyArrayListIterator(int index) {
            if (index >= 0 && index < size)
                currentPointer = index;
            else throw new IndexOutOfBoundsException();
        }

        public boolean hasNext() {
            return (currentPointer < size);
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) get(currentPointer++);
        }

    }

    private T[] elementData;
    private int size = 0;

    public MyArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
    }

    public MyArrayList() {
        elementData = (T[]) new Object[10];
    }

    public MyArrayList(MyArrayList that) {
        addAll(that);
    }

    public boolean add(T e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
        return true;
    }

    public void add(int index, T e) {
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = e;
        ++size;
    }

    public boolean addAll(Collection<? extends T> c) {
        ensureCapacity(size + c.size());
        Iterator iter = c.iterator();
        for (int i = 0; i < c.size(); ++i)
            elementData[size++] = (T) iter.next();
        return true;
    }

    public boolean addAll(ArrayList<? extends T> c) {
        ensureCapacity(size + c.size());
        for (int i = 0; i < c.size(); ++i)
            elementData[size++] = c.get(i);
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        ensureCapacity(size + c.size());
        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        Iterator iter = c.iterator();
        for (int i = index; i < index + c.size(); ++i)
            elementData[i] = (T) iter.next();
        size += c.size();
        return true;
    }

    public boolean addAll(int index, ArrayList<? extends T> c) {
        ensureCapacity(size + c.size());
        System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
        for (int i = index, j = 0; j < c.size(); ++i, ++j)
            elementData[i] = c.get(j);
        size += c.size();
        return true;
    }


    public void clear() {
        for (int i = 0; i < size; ++i)
            elementData[i] = null;
        size = 0;
    }

    public Object clone() {
        return new MyArrayList<>(this);
    }

    public boolean contains(T e) {
        for (int i = 0; i < size; ++i)
            if (elementData[i].equals(e))
                return true;
        return false;
    }

    public void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity < minCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            T[] oldData = (T[]) new Object[oldCapacity];
            for (int i = 0; i < oldCapacity; ++i)
                oldData[i] = elementData[i];
            elementData = (T[]) new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
        }
    }

    public T get(int index) {
        if (index >= 0 && index < size)
            return elementData[index];
        else throw new IndexOutOfBoundsException();
    }

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

    public boolean isEmpty() {
        return (size == 0);
    }

    public Iterator<T> iterator() {
        return new MyArrayListIterator<>();
    }

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

    public T remove(int index) {
        if (index >= 0 && index < size) {
            int numMoved = size - index - 1;
            T res = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
            elementData[--size] = null;
            return res;
        } else throw new IndexOutOfBoundsException();
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (elementData[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            remove(iter.next());
        }

        return res;
    }

    public T set(int index, T e) {
        if (index >= 0 && index < size) {
            elementData[index] = e;
        } else throw new IndexOutOfBoundsException();
        return e;
    }

    public int size() {
        return size;
    }

    public void sort(Comparator<? super T> c) {
        mergeSort(elementData, 0, size - 1, c);
    }


    private <T> void mergeSort(T[] a, int from, int to, Comparator<? super T> c) {
        if (from == to)
            return;
        int mid = (from + to) / 2;
        mergeSort(a, from, mid, c);
        mergeSort(a, mid + 1, to, c);
        merge(a, from, mid, to, c);
    }

    private <T> void merge(T[] a, int from, int mid, int to, Comparator<? super T> c) {
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

    public Object[] toArray() {
        Object[] resData = new Object[size];
        System.arraycopy(elementData, 0, resData, 0, size);
        return resData;
    }

    public T[] toArray(T[] a) {
        T[] resData = (T[]) new Object[size];
        System.arraycopy(elementData, 0, resData, 0, size);
        return resData;
    }

    public void trimToSize() {
        T[] oldData = (T[]) new Object[size];
        for (int i = 0; i < size; ++i)
            oldData[i] = elementData[i];
        elementData = (T[]) new Object[size];
        System.arraycopy(oldData, 0, elementData, 0, size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<T> that = (MyArrayList<T>) o;
        return size == that.size && Arrays.equals(elementData, that.elementData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementData, size);
    }
}
