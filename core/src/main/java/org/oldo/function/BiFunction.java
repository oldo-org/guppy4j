package org.oldo.function;

import org.oldo.reflect.MethodFinder;

/**
 * Serializable BiFunction with reflection abilities
 */
public interface BiFunction<T, P1, R> extends java.util.function.BiFunction<T, P1, R>, MethodFinder {
}
