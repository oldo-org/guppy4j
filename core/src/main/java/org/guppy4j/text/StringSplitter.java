package org.guppy4j.text;

/**
 * Splits String values into parts
 */
public interface StringSplitter {

    String[] split(String s, char separator);
}
