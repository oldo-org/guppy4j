package org.guppy4j;

/**
 * Wraps checked exceptions
 */
public final class Exceptions {

    public interface DoSomething {
        void doIt() throws Exception;
    }

    public static void tryTo(DoSomething ds) {
        try {
            ds.doIt();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
