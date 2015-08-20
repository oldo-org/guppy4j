package org.guppy4j;

import java.util.function.Function;

/**
 * Wraps checked exceptions
 */
public final class Exceptions {

    public interface DoSomething<E extends Exception> {
        void doIt() throws E;
    }

    public static <RE extends RuntimeException, E extends Exception> void tryCatchWrap(
            DoSomething<E> something,
            Class<E> exClass,
            Function<Exception, RE> exWrapper)
            throws RE {
        try {
            something.doIt();
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
}
