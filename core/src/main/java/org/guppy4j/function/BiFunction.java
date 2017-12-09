package org.guppy4j.function;

import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable BiFunction with reflection abilities
 */
public interface BiFunction<T, P1, R> extends java.util.function.BiFunction<T, P1, R>, MethodFinder {
}
