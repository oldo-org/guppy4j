package org.guppy4j.arrays;

import java.util.function.Function;

/**
 * TODO: Summarize intended functionality
 */
public class ArrayWrapper<T> {

    private final T[] array;
    private final Function<Integer, T[]> sizedArrayCreator;

    public ArrayWrapper(T[] array, Function<Integer, T[]> sizedArrayCreator) {
        this.array = array;
        this.sizedArrayCreator = sizedArrayCreator;
    }

    public T[] append(T[] other) {
        final int len1 = array.length;
        if (len1 == 0) {
            return other;
        }
        final int len2 = other.length;
        if (len2 == 0) {
            return array;
        }
        final T[] result = sizedArrayCreator.apply(len1 + len2);
        System.arraycopy(array, 0, result, 0, len1);
        System.arraycopy(other, 0, result, len1, len2);
        return result;
    }
}
