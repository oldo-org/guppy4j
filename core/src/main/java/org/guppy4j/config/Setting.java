package org.guppy4j.config;

/**
 * An encapsulated name/value pair
 */
public interface Setting {

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
     * @throws IllegalStateException if setting is null
     */
    String valueNotNull();

    /**
     * @return The value of the setting as a character
     */
    char asChar();

    /**
     * @return The converted value for the name
     * @throws NumberFormatException If value cannot be parsed as an int
     */
    int asInt();


    String[] valueSplitBy(char separator);

}
