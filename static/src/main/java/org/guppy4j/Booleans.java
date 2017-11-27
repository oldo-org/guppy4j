package org.guppy4j;

/**
 * Static helper methods for boolean values and Boolean objects
 */
public class Booleans {

    public static boolean not(boolean b) {
        return !b;
    }

    public static boolean both(boolean b1, boolean b2) {
        return b1 && b2;
    }

    public static boolean allOf(boolean... bArray) {
        for (boolean b : bArray) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public static boolean noneOf(boolean... bArray) {
        for (boolean b : bArray) {
            if (b) {
                return false;
            }
        }
        return true;
    }
}
