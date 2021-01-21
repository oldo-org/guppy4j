package org.oldo.datatree;

/**
 * Readable tree structure
 */
public interface ReadableTree extends ReadableData {

    Iterable<? extends ReadableTree> nodes(String expression);
}
