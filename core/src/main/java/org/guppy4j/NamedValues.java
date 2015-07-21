package org.guppy4j;

import org.guppy4j.logic.Predicate;

/**
 * Named values (usually String based)
 */
public interface NamedValues {

    /**
     * @param name Name of a named value
     * @return The corresponding named value object
     */
    NamedValue get(String name);

    /**
     * @return All named values
     */
    Iterable<NamedValue> all();

    /**
     * @param condition A boolean condition for settings
     * @return All named values that match the condition
     */
    Iterable<NamedValue> matches(Predicate<NamedValue> condition);

}
