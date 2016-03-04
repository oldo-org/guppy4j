package org.guppy4j.datatree;

/**
 * Readable tree structure
 */
public interface ReadableTree extends ReadablePayload {

    Iterable<? extends ReadableTree> nodes(String expression);
}
