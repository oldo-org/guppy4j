package org.oldo.log;

import org.slf4j.Logger;

import static org.slf4j.helpers.MessageFormatter.arrayFormat;

/**
 * Logs using an slf4j logger
 */
public class Slf4jLog implements Log {

    private final Logger logger;

    public Slf4jLog(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void as(Level level, String message, Object... parameters) {
        switch (level) {
            case trace:
                logger.trace(message, parameters);
                break;
            case debug:
                logger.debug(message, parameters);
                break;
            case info:
                logger.info(message, parameters);
                break;
            case warn:
                logger.warn(message, parameters);
                break;
            case error:
                logger.error(message, parameters);
                break;
        }
    }

    @Override
    public void as(Level level, Throwable t) {
        as(level, t, "");
    }

    @Override
    public void as(Level level, Throwable t, String messageFormat, Object... parameters) {
        if (isEnabled(level)) {
            final String message = arrayFormat(messageFormat, parameters).getMessage();
            as(level, t, message);
        }
    }

    private void as(Level level, Throwable t, String message) {
        switch (level) {
            case error:
                logger.error(message, t);
                break;
            case warn:
                logger.warn(message, t);
                break;
            case info:
                logger.info(message, t);
                break;
            case debug:
                logger.debug(message, t);
                break;
            case trace:
                logger.trace(message, t);
                break;
        }
    }

    @Override
    public boolean isEnabled(Level level) {
        switch (level) {
            case error:
                return logger.isErrorEnabled();
            case warn:
                return logger.isWarnEnabled();
            case info:
                return logger.isInfoEnabled();
            case debug:
                return logger.isDebugEnabled();
            case trace:
                return logger.isTraceEnabled();
            default:
                throw new IllegalArgumentException("Unknown log level: level");
        }
    }
}
