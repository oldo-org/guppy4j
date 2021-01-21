package org.oldo.function;


import org.oldo.reflect.MethodFinder;

/**
 * Serializable QuadConsumer with reflection abilities
 */
@FunctionalInterface
public interface QuadConsumer<P1, P2, P3, P4> extends MethodFinder {

    void accept(P1 p1, P2 p2, P3 p3, P4 p4);

}
