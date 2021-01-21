package org.oldo.text;

import static java.lang.String.format;

/**
 * Named String implementation
 */
public class NamedStringImpl implements NamedString {

    private final StringSplitter stringSplitter = new StringSplitterImpl();

    private final String name;
    private final String value;

    public NamedStringImpl(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public String getNotNull() {
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
    public String[] splitBy(char separator) {
        return value == null ? null : stringSplitter.split(value, separator);
    }
}
