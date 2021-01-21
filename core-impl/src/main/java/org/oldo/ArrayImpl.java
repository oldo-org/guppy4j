package org.oldo;

import java.util.function.Function;

/**
 * Appendable array wrapper
 */
public final class ArrayImpl<T> implements Array<T> {

    private final Function<Integer, T[]> sizedArrayCreator;
    private final T[] array;

    @SafeVarargs
    public ArrayImpl(Function<Integer, T[]> sizedArrayCreator, T... items) {
        this(items, sizedArrayCreator);
    }

    public ArrayImpl(T[] array, Function<Integer, T[]> sizedArrayCreator) {
        this.array = array;
        this.sizedArrayCreator = sizedArrayCreator;
    }

    @Override
    public T[] content() {
        return array;
    }

    @Override
    public Array<T> append(Array<T> other) {
        return append(other.content());
    }

    @SafeVarargs
    @Override
    public final Array<T> append(T... other) {
        if (other.length == 0) {
            return this;
        }
        final int length = array.length;
        if (length == 0) {
            return newFrom(other);
        }
        final T[] result = sizedArrayCreator.apply(length + other.length);
        System.arraycopy(array, 0, result, 0, length);
        System.arraycopy(other, 0, result, length, other.length);
        return newFrom(result);
    }

    @Override
    public Array<T> newFrom(T[] array) {
        return new ArrayImpl<>(array, sizedArrayCreator);
    }
}
