package org.guppy4j.reflect;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * Serializable functional base interface with reflection abilities
 * Based on ideas from https://github.com/benjiman/lambda-type-references
 */
public interface MethodFinder extends Serializable {

    default SerializedLambda serialized() {
        try {
            final Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            return (SerializedLambda) replaceMethod.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    default Class<?> getContainingClass() {
        try {
            final String className = serialized().getImplClass().replace('/', '.');
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    default Method method() {
        final SerializedLambda lambda = serialized();
        final Class<?> containingClass = getContainingClass();
        return Arrays.stream(containingClass.getDeclaredMethods())
                .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                .findFirst()
                .orElseThrow(UnableToGuessMethodException::new);
    }

    default Parameter parameter(int n) {
        return method().getParameters()[n];
    }

    class UnableToGuessMethodException extends RuntimeException {
    }
}
