package org.guppy4j;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Static helper methods for list objects
 */
public class Lists {

    /**
     * @param list A list
     * @param <T>  The list element type
     * @return A randomly chosen element from the provided list, or null if the list is null or empty
     */
    public static <T> T getRandomFrom(List<T> list) {
        return Iterables.isNullOrEmpty(list) ? null : list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
