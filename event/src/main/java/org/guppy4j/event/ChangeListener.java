package org.guppy4j.event;

/**
 * Change listener
 */
public interface ChangeListener<T> {

    void handleChange(T before, T after);
}
