package com.company;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Interface of custom ArrayList collection
 *
 * @param <T> Type of ArrayList elements
 * @author Aleksandr Dvorak
 */
public interface ArrayList<T> extends Iterable<T> {
    /**
     * Adds element of generic type to the end of ArrayList
     *
     * @param e Element to be added
     * @return Always true, as adding similar objects is enabled in ArrayList
     */
    public boolean add(T e);

    /**
     * Adds element of generic type to ArrayList by index
     *
     * @param index Index where element should be added
     * @param e     Element to be added
     */
    public void add(int index, T e);

    /**
     * Adds all elements of collection to the ArrayList
     *
     * @param c Collection from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(Collection<? extends T> c);

    /**
     * Adds all elements of some ArrayList to this ArrayList
     *
     * @param c ArrayList from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(ArrayList<? extends T> c);

    /**
     * Adds all elements of collection to this ArrayList
     *
     * @param index Index to which elements should be added in ArrayList
     * @param c     Collection from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(int index, Collection<? extends T> c);

    /**
     * Adds all elements of some ArrayList to this ArrayList
     *
     * @param index Index to which elements should be added in ArrayList
     * @param c     ArrayList from which elements are taken
     * @return Always True, as adding similar objects is enabled in ArrayList
     */
    public boolean addAll(int index, ArrayList<? extends T> c);

    /**
     * Clears ArrayList
     */
    public void clear();

    /**
     * Clones ArrayList instance
     *
     * @return Clone
     */
    public Object clone();

    /**
     * Checks if ArrayList contains element e of type T
     *
     * @param e Element to be checked
     * @return true - if ArrayList contains element, false - if not
     */
    public boolean contains(T e);

    /**
     * Enlarges capacity of ArrayList if neeeded
     *
     * @param minCapacity Minimal capacity needed
     */
    public void ensureCapacity(int minCapacity);

    /**
     * Gets element of ArrayList by index
     *
     * @param index Index
     * @return Element of generic type
     */
    public T get(int index);

    /**
     * Searches first occurrence of some object in ArrayList, if it exists
     *
     * @param o Object to be searched
     * @return Idex of object in ArrayList, or -1, if it doesn't exist in ArrayList
     */
    public int indexOf(Object o);

    /**
     * Checks, weather ArrayList is empty or not
     *
     * @return true - if ArrayList is empty, false - if not
     */
    public boolean isEmpty();

    /**
     * Get iterator of ArrayList
     *
     * @return Iterator of ArrayList
     */
    public Iterator<T> iterator();

    /**
     * Searches last occurrence of some object in ArrayList, if it exists
     *
     * @param o Object to be searched
     * @return Idex of object in ArrayList, or -1, if it doesn't exist in ArrayList
     */
    public int lastIndexOf(Object o);

    /**
     * Removes element from ArrayList by index
     *
     * @param index Index of some element
     * @return Removed element
     */
    public T remove(int index);

    /**
     * Removes object from ArrayList
     *
     * @param o Object to be removed
     * @return true - if object was removed, false - if wasn't
     */
    public boolean remove(Object o);

    /**
     * Removes all elements of some collection from ArrayList
     *
     * @param c Collection from which elements are compared with elements from ArrayList
     * @return True - if at least one element was removed, false - if none of elements were removed
     */
    public boolean removeAll(Collection<?> c);

    /**
     * Removes all elements of some ArrayList from ArrayList
     *
     * @param c ArrayList from which elements are compared with elements from this ArrayList
     * @return True - if at least one element was removed, false - if none of elements were removed
     */
    public boolean removeAll(MyArrayList<?> c);

    /**
     * Sets element of ArrayList by index
     *
     * @param index Index
     * @return Element of generic type
     */
    public T set(int index, T e);

    /**
     * Get size of ArrayList
     *
     * @return Int size of ArrayList
     */
    public int size();

    /**
     * Sorts array of elements inside ArrayList
     *
     * @param c Comparator for comparison of objects
     */
    public void sort(Comparator<? super T> c);

    /**
     * Gets array of elements of ArrayList with the same size and type
     *
     * @return Array of elements of ArrayList
     */
    public Object[] toArray();

    /**
     * Gets array of elements of ArrayList with the same size and type
     *
     * @param a   Array of generic tyep for casting
     * @param <T> Generic Type
     * @return Array of elements of ArrayList
     */
    public <T> T[] toArray(T[] a);

    /**
     * Shrinks capacity of ArrayList to it's size
     */
    public void trimToSize();
}
