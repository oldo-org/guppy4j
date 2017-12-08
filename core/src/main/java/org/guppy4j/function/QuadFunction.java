package org.guppy4j.function;

/**
 * Function that accepts 4 parameters
 */
@FunctionalInterface
public interface QuadFunction<P1, P2, P3, P4, R> {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4);

}
