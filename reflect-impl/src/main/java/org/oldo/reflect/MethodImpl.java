package org.oldo.reflect;

import java.util.Objects;

/**
 * A method from  a declaring type
 */
public final class MethodImpl<T> implements Method<T> {

    private final java.lang.reflect.Method method;
    private final Class<T> type;

    public MethodImpl(java.lang.reflect.Method method, Class<T> type) {
        Objects.requireNonNull(type);
        this.type = type;
        Objects.requireNonNull(method);
        this.method = method;
    }

    @Override
    public java.lang.reflect.Method getMethod() {
        return method;
    }

    @Override
    public Class<T> getDeclaringClass() {
        return type;
    }
}
