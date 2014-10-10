package org.guppy4j.messaging;

/**
 * Tree structured data
 */
public interface Tree extends WritablePayload, ReadableTree {

    Iterable<Tree> nodes(String expression);

}
