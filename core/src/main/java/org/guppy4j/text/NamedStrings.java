package org.guppy4j.text;

import java.util.function.Predicate;

/**
 * Named values (usually String based)
 */
public interface NamedStrings {

    /**
     * @param name Name of a named value
     * @return The corresponding named value object
     */
    NamedString get(String name);

    /**
     * @return All named values
     */
    Iterable<NamedString> all();

    /**
     * @param condition A boolean condition for settings
     * @return All named values that match the condition
     */
    Iterable<NamedString> matches(Predicate<NamedString> condition);

}
