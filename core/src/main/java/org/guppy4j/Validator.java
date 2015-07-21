package org.guppy4j;

/**
 * Generic validation methods
 */
public interface Validator {

    void checkExists(String s, String name);

    void checkLength(String s, int expectedLength, String name);

    void checkNotNull(Object o, String name);

}
