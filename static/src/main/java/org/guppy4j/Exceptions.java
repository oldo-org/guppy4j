package org.guppy4j;

import java.util.function.Function;

/**
 * Wraps checked exceptions
 */
public final class Exceptions {

    public interface DoSomething<E extends Exception> {
        void doIt() throws E;
    }

    public interface ReturnSomething<P, R, E extends Exception> {
        R doIt(P parameter) throws E;
    }

    public static <P, R, RE extends RuntimeException, E extends Exception>
    R tryCatchWrap(
            ReturnSomething<P, R, E> something,
            P parameter,
            Class<E> exClass,
            Function<Exception, RE> exWrapper) {
        try {
            return something.doIt(parameter);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            if (exClass.isInstance(e)) {
                throw exWrapper.apply(e);
            } else {
                // cannot happen
                throw new RuntimeException("Unexpected checked exception", e);
            }
        }
    }

    public static <RE extends RuntimeException, E extends Exception>
    void tryCatchWrap(DoSomething<E> something,
                      Class<E> exClass, Function<Exception, RE> exWrapper)
            throws RE {
        tryCatchWrap(parameter -> {
                    something.doIt();
                    return null;
                },
                null, exClass, exWrapper);
    }
}
