package org.guppy4j;

/**
 * Generic validation methods
 */
public class ValidatorImpl implements Validator {

    public void checkExists(String value, String name) {
        checkNotNull(value, name);
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is empty");
        }
    }

    @Override
    public void checkLength(String s, int expectedLength, String name) {
        if (s.length() != expectedLength) {
            throw new IllegalArgumentException(name + " must be "
                    + expectedLength + " character long");
        }
    }

    @Override
    public void checkNotNull(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException(name + " is null");
        }
    }
}
