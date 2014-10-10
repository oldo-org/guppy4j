package org.guppy4j.config;

import org.guppy4j.logic.Predicate;

/**
 * Named String values plus optional conversion
 * to other primitive types
 */
public interface Settings {

    /**
     * @param name Name of the setting
     * @return The setting
     */
    Setting get(String name);

    /**
     * @return All settings
     */
    Iterable<Setting> all();

    /**
     * @param condition A boolean condition for settings
     * @return A settings that match the condition
     */
    Iterable<Setting> matches(Predicate<Setting> condition);

}
