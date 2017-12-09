package org.guppy4j.function;

import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable QuadFunctio with reflection abilities
 */
@FunctionalInterface
public interface QuadFunction<P1, P2, P3, P4, R> extends MethodFinder {

    R apply(P1 p1, P2 p2, P3 p3, P4 p4);

}
