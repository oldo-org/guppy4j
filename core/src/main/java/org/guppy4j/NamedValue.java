package org.guppy4j;

/**
 * An encapsulated name/value pair
 */
public interface NamedValue {

    /**
     * @return The name of the setting
     */
    String name();

    /**
     * @return The value of the setting
     */
    String value();

    /**
     * @return The value of the setting, if not null
     * @throws IllegalStateException if setting value is null
     */
    String valueNotNull();

    /**
     * @return The value of the setting as a character
     */
    char asChar();

    /**
     * @return The value of the setting as an int
     * @throws NumberFormatException If value cannot be parsed as an int
     */
    int asInt();

    /**
     * @param separator Separator character
     * @return All the separated parts of the value
     */
    String[] valueSplitBy(char separator);

}
