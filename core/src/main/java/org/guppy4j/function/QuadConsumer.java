package org.guppy4j.function;

/**
 * Consumer that accepts 4 parameters
 */
@FunctionalInterface
public interface QuadConsumer<P1, P2, P3, P4> {

    void accept(P1 p1, P2 p2, P3 p3, P4 p4);

}
