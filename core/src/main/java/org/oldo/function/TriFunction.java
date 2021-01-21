package org.oldo.function;


import org.oldo.reflect.MethodFinder;

/**
 * Serializable TriFunction with reflection abilities
 */
@FunctionalInterface
public interface TriFunction<P1, P2, P3, R> extends MethodFinder {

    R apply(P1 p1, P2 p2, P3 p3);

}
