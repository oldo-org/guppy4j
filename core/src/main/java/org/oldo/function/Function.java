package org.oldo.function;

import org.oldo.reflect.MethodFinder;

/**
 * Serializable Function with reflection abilities
 */
public interface Function<T, R> extends java.util.function.Function<T, R>, MethodFinder {

    // serializable function
}
