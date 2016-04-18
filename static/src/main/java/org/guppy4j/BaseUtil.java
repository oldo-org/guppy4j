package org.guppy4j;

/**
 * Core logic etc
 */
public class BaseUtil {

    public static boolean exists(String s) {
        return not(isNullOrEmpty(s));
    }

    public static boolean exists(Iterable<?> iterable) {
        return not(isNullOrEmpty(iterable));
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isNullOrEmpty(Iterable<?> iterable) {
        return iterable == null || not(iterable.iterator().hasNext());
    }

    public static boolean bothNullOrEqual(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static boolean not(boolean b) {
        return !b;
    }

    /**
     * Conditional casting, useful for typical equals() implementations
     *
     * @param o   The other object
     * @param c   The class to check for
     * @param <T> The generic type of the target class
     * @return The object cast as a T, or null if o.getClass() is not c
     */
    public static <T> T castIfSameClass(Object o, Class<T> c) {
        return o != null && o.getClass() == c ? c.cast(o) : null;
    }

    /**
     * Conditional casting, useful for equals() implementations using instanceof
     *
     * @param o   The other object
     * @param c   The class to check for
     * @param <T> The generic type of the target class
     * @return The object cast as a T, or null if o is not an instance of c
     */
    public static <T> T castIfInstance(Object o, Class<T> c) {
        return o != null && c.isInstance(o) ? c.cast(o) : null;
    }
}
