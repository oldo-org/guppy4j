package org.guppy4j;

import org.guppy4j.util.StringSplitter;
import org.guppy4j.util.StringSplitterImpl;

import static java.lang.String.format;

/**
 * Setting implementation
 */
public class NamedValueImpl implements NamedValue {

    private final StringSplitter stringSplitter = new StringSplitterImpl();

    private final String name;
    private final String value;

    public NamedValueImpl(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String valueNotNull() {
        if (value != null) {
            return value;
        } else {
            throw new IllegalStateException(format("Property '%s' is null", name));
        }
    }

    @Override
    public char asChar() {
        if (value.length() == 1) {
            return value.charAt(0);
        } else {
            throw new IllegalStateException(format(
                    "Value of property '%s' is not a single char: '%s'", name, value));
        }
    }

    @Override
    public int asInt() {
        return Integer.parseInt(value);
    }

    @Override
    public String[] valueSplitBy(char separator) {
        return value == null ? null : stringSplitter.split(value, separator);
    }
}
