package org.oldo.log;

/**
 * Log abstraction
 */
public interface Log {

    /**
     * The log levels
     */
    enum Level {
        error, warn, info, debug, trace
    }

    /**
     * Logs a throwable at the specified log level
     *
     * @param level log level
     * @param t     the throwable
     */
    void as(Level level, Throwable t);

    void as(Level level, Throwable t, String message, Object... parameters);

    void as(Level level, String message, Object... parameters);

    /**
     * @param level A log level
     * @return If this log level is enabled
     */
    boolean isEnabled(Level level);

    /**
     * A Log instance that does nothing
     */
    Log NONE = new Log() {
        @Override
        public void as(Level level, Throwable t) {
            // do nothing
        }

        @Override
        public void as(Level level, Throwable t, String message, Object... parameters) {
            // do nothing
        }

        @Override
        public void as(Level level, String message, Object... parameters) {
            // do nothing
        }

        @Override
        public boolean isEnabled(Level level) {
            return false;
        }
    };

}
