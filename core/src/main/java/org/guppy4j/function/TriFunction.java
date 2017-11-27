package org.guppy4j.function;

/**
 * Function that accepts 3 parameters
 */
@FunctionalInterface
public interface TriFunction<P1, P2, P3, R> {

    R apply(P1 p1, P2 p2, P3 p3);

}
