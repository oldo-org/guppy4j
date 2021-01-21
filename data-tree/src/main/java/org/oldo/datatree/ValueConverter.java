package org.oldo.datatree;

/**
 * Converts named values according to data format rules
 */
public interface ValueConverter {

    String result(String name, String value);

}
