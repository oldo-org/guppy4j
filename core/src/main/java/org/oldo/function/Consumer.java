package org.oldo.function;

import org.oldo.reflect.MethodFinder;

/**
 * Serializable Consumer with reflection abilities
 */
public interface Consumer<T> extends java.util.function.Consumer<T>, MethodFinder {
}
