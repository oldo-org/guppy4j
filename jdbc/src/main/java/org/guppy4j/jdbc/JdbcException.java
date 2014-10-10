package org.guppy4j.jdbc;

/**
 * Generic JDBC runtime exception (usually wraps an SQLException)
 */
public class JdbcException extends RuntimeException {

    public JdbcException(Throwable cause) {
        super(cause);
    }

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
