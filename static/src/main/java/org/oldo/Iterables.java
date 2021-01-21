package org.oldo;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.oldo.Booleans.not;

/**
 * Static helper methods for iterable objects
 */
public final class Iterables {

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static boolean exists(Iterable<?> iterable) {
        return not(isNullOrEmpty(iterable));
    }

    public static boolean isNullOrEmpty(Iterable<?> iterable) {
        return iterable == null || not(iterable.iterator().hasNext());
    }
}
