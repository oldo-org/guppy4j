package org.guppy4j.logic;

/**
 * Converts from one data type to another
 */
public interface Function<X, T> {

    /**
     * @param x Input value (single parameter)
     * @return Resulting object
     */
    T result(X x);
}
