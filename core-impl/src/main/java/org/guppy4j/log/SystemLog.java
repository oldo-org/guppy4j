package org.guppy4j.log;

import java.io.PrintStream;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * Logs to stdout and stderr
 */
public class SystemLog implements Log {

    private static final PrintStream STD_ERROR = err;
    private static final PrintStream STD_OUT = out;

    @Override
    public void as(Level level, Throwable t) {
        t.printStackTrace(getStream(level));
    }

    @Override
    public void as(Level level, Throwable t, String message, Object... parameters) {
        as(level, message, parameters);
        as(level, t);
    }

    @Override
    public void as(Level level, String message, Object... parameters) {
        final PrintStream ps = getStream(level);
        ps.println(message);
        for (Object parameter : parameters) {
            ps.println(parameter);
        }
    }

    @Override
    public boolean isEnabled(Level level) {
        return true;
    }

    private static PrintStream getStream(Level level) {
        switch (level) {
            case error:
            case warn:
                return STD_ERROR;
            default:
                return STD_OUT;
        }
    }
}
