package org.oldo.log;

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
    LogProvider NONE = c -> Log.NONE;
}
