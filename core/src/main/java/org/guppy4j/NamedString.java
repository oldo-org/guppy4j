package org.guppy4j;

/**
 * An encapsulated name/value pair
 */
public interface NamedString {

    /**
     * @return The name of the setting
     */
    String name();

    /**
     * @return The value of the setting
     */
    String get();

    /**
     * @return The value of the setting, if not null
     * @throws IllegalStateException if setting value is null
     */
    String getNotNull();

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
    String[] splitBy(char separator);

}
