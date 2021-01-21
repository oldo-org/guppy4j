package org.oldo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Invocation handler that remembers the last invoked method
 */
public final class MethodCapturer implements InvocationHandler, Supplier<Method> {

    private Method method;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        this.method = method;
        final Class<?> returnType = method.getReturnType();
        return DefaultValue.forType(returnType);
    }

    @Override
    public Method get() {
        return method;
    }
}
