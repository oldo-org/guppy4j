package org.oldo.reflect;

import java.lang.reflect.InvocationHandler;

/**
 * Creates proxy instances for given target type and invocation handler
 */
@FunctionalInterface
public interface ProxyBuilder<T> {

    T buildProxy(Class<T> targeType, InvocationHandler invocationHandler);

}
