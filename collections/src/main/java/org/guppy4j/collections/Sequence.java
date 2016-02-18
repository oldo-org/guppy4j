package org.guppy4j.collections;

/**
 * A sequence allows finding the next and previous item for any given item
 */
public interface Sequence<T> {

    T previous(T item);

    T next(T item);

}
