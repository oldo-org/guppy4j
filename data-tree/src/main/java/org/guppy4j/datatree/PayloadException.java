package org.guppy4j.datatree;

/**
 * Runtime exception while processing a payload
 */
public class PayloadException extends RuntimeException {

    public PayloadException(String message) {
        super(message);
    }

    public PayloadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PayloadException(Throwable cause) {
        super(cause);
    }
}
