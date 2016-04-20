package org.guppy4j;

/**
 * Provides more readable comparison methods than Comparable
 */
public interface Order<T> {

    boolean isGreaterThan(T other);

    boolean isLessThan(T other);

    boolean isEqualTo(T other);
}