package org.guppy4j.logic;

/**
 * Boolean function, i.e. a condition that
 * can only be true or false for any given
 * input values
 */
public interface Predicate<T> {

    boolean isTrueFor(T t);
}
