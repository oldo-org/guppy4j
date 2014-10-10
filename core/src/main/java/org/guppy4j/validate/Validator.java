package org.guppy4j.validate;

/**
 * Generic validation methods
 */
public interface Validator {

    void checkExists(String value, String name);

    void validateLength(String s, int expectedLength, String name);
}
