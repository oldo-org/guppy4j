package org.guppy4j.reflect;

/**
 * Type-preserving method wrapper
 */
public interface Method<T> {

    java.lang.reflect.Method getMethod();

    Class<T> getDeclaringClass();

    default String name() {
        return getMethod().getName();
    }
}
