package org.guppy4j.function;

import org.guppy4j.reflect.MethodFinder;

/**
 * Serializable Consumer with reflection abilities
 */
public interface Consumer<T> extends java.util.function.Consumer<T>, MethodFinder {
}
