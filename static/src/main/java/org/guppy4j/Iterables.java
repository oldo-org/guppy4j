package org.guppy4j;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Static helper methods for Iterable objects
 */
public final class Iterables {

    public static <T> Stream<T> stream(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
