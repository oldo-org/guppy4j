package org.guppy4j.datatree;

/**
 * Writable message (allows setting expression values)
 */
public interface WritablePayload extends Payload {

    boolean set(String expression, String value);

}
