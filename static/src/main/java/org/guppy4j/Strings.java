package org.guppy4j;

/**
 * Static helper methods for String objects
 */
public class Strings {

    public static boolean exists(String s) {
        return Booleans.not(isNullOrEmpty(s));
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

}
