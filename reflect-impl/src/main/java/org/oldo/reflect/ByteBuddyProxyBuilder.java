package org.oldo.reflect;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.lang.reflect.InvocationHandler;

import static net.bytebuddy.matcher.ElementMatchers.any;


public final class ByteBuddyProxyBuilder<T> implements ProxyBuilder<T> {

    @Override
    public T buildProxy(Class<T> type, InvocationHandler invocationHandler) {
        try {
            return new ByteBuddy()
                    .subclass(type)
                    .method(any()).intercept(InvocationHandlerAdapter.of(invocationHandler))
                    .make().load(type.getClassLoader()).getLoaded()
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
