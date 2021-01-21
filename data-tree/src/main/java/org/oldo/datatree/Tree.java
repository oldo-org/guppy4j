package org.oldo.datatree;

/**
 * Tree structured data
 */
public interface Tree extends WritableData, ReadableTree {

    Iterable<Tree> nodes(String expression);

}
