package org.guppy4j.function;

import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable Function with reflection abilities
 */
public interface Function<T, R> extends java.util.function.Function<T, R>, MethodFinder {

    // serializable function
}
