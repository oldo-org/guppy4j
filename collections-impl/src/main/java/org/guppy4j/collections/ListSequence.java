package org.guppy4j.collections;

import static java.util.Collections.unmodifiableList;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * A simple item sequence that allows finding the next and previous item for any given item,
 * based on a provided list of items that defines the order
 */
public final class ListSequence<T> implements Sequence<T> {

    private static final boolean FAILED = false;
    private static final int NOT_FOUND = -1;

    private static final int ONE_FORWARD = +1;
    private static final int ONE_BACKWARDS = -1;

    private final List<T> list;

    @SafeVarargs
    public ListSequence(T... items) {
        this(Arrays.asList(items));
    }

    public ListSequence(List<T> aList) {
        list = unmodifiableList(aList);
        ensureNoNulls(list);
        ensureNoDuplicates(list);
    }

    @Override
    public T previous(T item) {
        return stepFrom(item, ONE_BACKWARDS);
    }

    @Override
    public T next(T item) {
        return stepFrom(item, ONE_FORWARD);
    }

    private T stepFrom(T item, int steps) {
        final int index = list.indexOf(item);
        if (index == NOT_FOUND) {
            throw new IllegalArgumentException("Item not found in sequence: " + item);
        }
        return list.get(index + steps);
    }

    private static <T> void ensureNoNulls(Collection<T> collection) {
        if (collection.contains(null)) {
            throw new IllegalArgumentException("Null items not allowed in sequence");
        }
    }

    private static <T> void ensureNoDuplicates(Iterable<T> items) {
        final Collection<T> set = new HashSet<>();
        for (T item : items) {
            if (set.add(item) == FAILED) {
                throw new IllegalArgumentException("Duplicate item: " + item);
            }
        }
    }
}
