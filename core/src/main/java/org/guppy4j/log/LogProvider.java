package org.guppy4j.log;

/**
 * Provides log instances
 */
public interface LogProvider {

    /**
     * @param c A class
     * @return A logger instance for the class
     */
    Log getLog(Class<?> c);

    /**
     * A LogProvider instance that does nothing
     */
    LogProvider NONE = new LogProvider() {
        @Override
        public Log getLog(Class<?> c) {
            return Log.NONE;
        }
    };
}
