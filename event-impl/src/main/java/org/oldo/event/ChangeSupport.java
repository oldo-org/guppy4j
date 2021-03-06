package org.oldo.event;

import java.util.Collection;
import java.util.LinkedList;

import static org.oldo.Booleans.not;
import static org.oldo.Objects.bothNullOrEqual;

/**
 * Helper class for change event propagation
 */
public class ChangeSupport<T> implements ChangePropagator<T> {

    private final Collection<ChangeListener<T>> listeners = new LinkedList<>();

    @Override
    public void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void handleChange(T before, T after) {
        if (not(bothNullOrEqual(before, after))) {
            for (ChangeListener<T> listener : listeners) {
                listener.handleChange(before, after);
            }
        }
    }
}
