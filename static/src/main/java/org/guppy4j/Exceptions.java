package org.guppy4j;

import java.util.function.Function;

/**
 * Wraps checked exceptions
 */
public final class Exceptions {

    public interface DoSomething<E extends Exception> {
        void doIt() throws E;
    }

    public static <T extends RuntimeException, E extends Exception> void tryCatchWrap(
            DoSomething<E> something,
            Class<E> exClass,
            Function<Exception, T> exWrapper) {
        try {
            something.doIt();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            if (exClass.isInstance(e)) {
                throw exWrapper.apply(e);
            } else {
                // cannot happen
                throw new RuntimeException();
            }
        }
    }
}
