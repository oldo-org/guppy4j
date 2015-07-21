package org.guppy4j.messaging;

/**
 * Converts named values according to data format rules
 */
public interface ValueConverter {

    String result(String name, String value);

}
