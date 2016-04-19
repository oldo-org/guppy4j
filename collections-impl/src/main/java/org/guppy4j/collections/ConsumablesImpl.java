package org.guppy4j.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Consumables using a collection, with support for optional item removal
 */
public final class ConsumablesImpl<T> implements Consumables<T> {

    private final Collection<T> collection;
    private final boolean remove;

    public ConsumablesImpl() {
        this(new ArrayList<>(), true);
    }

    public ConsumablesImpl(Collection<T> collection, boolean remove) {
        this.collection = collection;
        this.remove = remove;
    }

    @Override
    public void add(T t) {
        collection.add(t);
    }

    @Override
    public void remove(T t) {
        collection.remove(t);
    }

    @Override
    public void applyToAll(Consumer<? super T> consumer) {
        for (Iterator<T> iterator = collection.iterator(); iterator.hasNext(); ) {
            final T t = iterator.next();
            consumer.accept(t);
            if (remove) {
                iterator.remove();
            }
        }
    }
}
