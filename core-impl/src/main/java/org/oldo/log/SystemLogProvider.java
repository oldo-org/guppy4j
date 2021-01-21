package org.oldo.log;

/**
 * Provides a single system console log instance
 */
public final class SystemLogProvider implements LogProvider {

    private final Log log = new SystemLog();

    @Override
    public Log getLog(Class<?> clazz) {
        return log;
    }
}
