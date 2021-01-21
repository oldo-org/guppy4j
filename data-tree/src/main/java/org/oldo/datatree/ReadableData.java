package org.oldo.datatree;

/**
 * Message that makes text content of nodes available
 * (via expressions, e.g. xpath)
 */
public interface ReadableData extends Data {

    String get(String expression);
}
