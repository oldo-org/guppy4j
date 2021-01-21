package org.oldo.collections;

/**
 * A sequence allows finding the next and previous item for any given item
 */
public interface Sequence<T> {

    /**
     * @param item An item in the sequence
     * @return The previous item in the sequence, or null if the provided item is the first in the sequence
     */
    T previous(T item);

    /**
     * @param item An item in the sequence
     * @return The next item in the sequence, or null if the provided item is the last in the sequence
     */
    T next(T item);

}
