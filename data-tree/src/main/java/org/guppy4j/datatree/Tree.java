package org.guppy4j.datatree;

/**
 * Tree structured data
 */
public interface Tree extends WritablePayload, ReadableTree {

    Iterable<Tree> nodes(String expression);

}
