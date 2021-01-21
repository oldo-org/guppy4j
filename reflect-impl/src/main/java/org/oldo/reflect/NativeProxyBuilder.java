package org.oldo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Builds regular Java dynamic proxies (no dependency on 3rd party libraries)
 */
public final class NativeProxyBuilder<T> implements ProxyBuilder<T> {

    @Override
    public T buildProxy(Class<T> targetType, InvocationHandler invocationHandler) {
        final Object proxy = Proxy.newProxyInstance(
                targetType.getClassLoader(),
                new Class<?>[]{targetType},
                invocationHandler);
        return (T) proxy;
    }
}
