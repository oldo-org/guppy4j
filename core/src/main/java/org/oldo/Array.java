package org.oldo;

/**
 * Appendable array
 */
public interface Array<T> {

    T[] content();

    Array<T> append(Array<T> other);

    Array<T> append(T... other);

    Array<T> newFrom(T[] array);

}
