package org.guppy4j.messaging;

/**
 * Writable message (allows setting expression values)
 */
public interface WritablePayload extends Payload {

    void set(String expression, String value);

}
