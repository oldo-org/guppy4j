package org.guppy4j.function;


import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable TriConsumer with reflection abilities
 */
@FunctionalInterface
public interface TriConsumer<P1, P2, P3> extends MethodFinder {

    void accept(P1 p1, P2 p2, P3 p3);

}
