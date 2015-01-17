package org.guppy4j.messaging;

/**
 * Writable message (allows setting expression values)
 */
public interface WritablePayload extends Payload {

    boolean set(String expression, String value);

}
