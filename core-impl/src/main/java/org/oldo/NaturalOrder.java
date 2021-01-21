package org.oldo;

/**
 * An Order based on a Comparable
 */
public class NaturalOrder<T extends Comparable<T>> implements Order<T> {

    public static <X extends Comparable<X>> NaturalOrder<X> the(Comparable<X> comparable) {
        return new NaturalOrder<>(comparable);
    }

    private final Comparable<T> comparable;

    public NaturalOrder(Comparable<T> comparable) {
        this.comparable = comparable;
    }

    @Override
    public boolean isGreaterThan(T other) {
        return comparable.compareTo(other) > 0;
    }

    @Override
    public boolean isLessThan(T other) {
        return comparable.compareTo(other) < 0;
    }

    @Override
    public boolean isEqualTo(T other) {
        return comparable.compareTo(other) == 0;
    }
}