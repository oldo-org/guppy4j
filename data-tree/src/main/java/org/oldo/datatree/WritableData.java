package org.oldo.datatree;

/**
 * Writable message (allows setting expression values)
 */
public interface WritableData extends Data {

    boolean set(String expression, String value);

}
