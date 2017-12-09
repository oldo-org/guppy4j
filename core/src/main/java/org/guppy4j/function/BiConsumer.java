package org.guppy4j.function;

import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable BiConsumer with reflection abilities
 */
public interface BiConsumer<T, P1> extends java.util.function.BiConsumer<T, P1>, MethodFinder {

    // no additional methods
}
