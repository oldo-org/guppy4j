package org.guppy4j.log;

/**
 * Log abstraction
 */
public interface Log {

    enum Level {
        error, warn, info, debug, trace
    }

    void as(Level level, Throwable t);

    void as(Level level, Throwable t, String message, Object... parameters);

    void as(Level level, String message, Object... parameters);

}
