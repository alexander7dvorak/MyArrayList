package com.company;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public interface ArrayList<T> extends Iterable<T> {
    public boolean add(T e);

    public void add(int index, T e);

    public boolean addAll(Collection<? extends T> c);

    public boolean addAll(ArrayList<? extends T> c);

    public boolean addAll(int index, Collection<? extends T> c);

    public boolean addAll(int index, ArrayList<? extends T> c);

    public void clear();

    public Object clone();

    public boolean contains(T e);

    public void ensureCapacity(int minCapacity);

    public T get(int index);

    public int indexOf(Object o);

    public boolean isEmpty();

    public Iterator<T> iterator();

    public int lastIndexOf(Object o);

    public T remove(int index);

    public boolean remove(Object o);

    public boolean removeAll(Collection<?> c);

    public T set(int index, T e);

    public int size();

    public void sort(Comparator<? super T> c);

    public Object[] toArray();

    public T[] toArray(T[] a);

    public void trimToSize();
}
