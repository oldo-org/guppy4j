package org.guppy4j.validate;

/**
 * Generic validation methods
 */
public class ValidatorImpl implements Validator {

    public void checkExists(String value, String name) {
        if (value == null) {
            throw new IllegalArgumentException(name + " is null");
        }
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is empty");
        }
    }

    @Override
    public void validateLength(String s, int expectedLength, String name) {
        if (s.length() != expectedLength) {
            throw new IllegalArgumentException(name + " must be "
                    + expectedLength + " character long");
        }
    }
}
