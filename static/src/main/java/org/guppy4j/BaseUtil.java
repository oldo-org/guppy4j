package org.guppy4j;

/**
 * Core logic etc
 */
public class BaseUtil {

    public static boolean exists(String s) {
        return not(isNullOrEmpty(s));
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean bothNullOrEqual(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    public static boolean not(boolean b) {
        return !b;
    }
}
