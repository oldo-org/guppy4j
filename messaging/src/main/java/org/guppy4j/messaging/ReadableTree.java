package org.guppy4j.messaging;

/**
 * Readable tree structure
 */
public interface ReadableTree extends ReadablePayload {

    Iterable<? extends ReadableTree> nodes(String expression);
}
