package org.oldo.collections;

import java.util.function.Consumer;

/**
 * A bunch of items that can be fed to Consumer instances
 */
public interface Consumables<T> {

    void add(T t);

    void remove(T t);

    void applyToAll(Consumer<? super T> consumer);
}
