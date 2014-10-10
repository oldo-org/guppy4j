package org.guppy4j.logic;

/**
 * Binary functions
 */
public interface Function2<X1, X2, T> {

    /**
     * @param x1 1st function parameter
     * @param x2 2nd function parameter
     * @return Resulting object
     */
    T result(X1 x1, X2 x2);
}
