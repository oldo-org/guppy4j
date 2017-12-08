package org.guppy4j.reflect;

/**
 * Default value for each of the known Java type
 */
public final class DefaultValue {

    // These gets initialized to their default values
    private static boolean DEFAULT_BOOLEAN;
    private static byte DEFAULT_BYTE;
    private static short DEFAULT_SHORT;
    private static int DEFAULT_INT;
    private static long DEFAULT_LONG;
    private static float DEFAULT_FLOAT;
    private static double DEFAULT_DOUBLE;

    public static Object forType(Class<?> type) {
        if (type.equals(boolean.class)) {
            return DEFAULT_BOOLEAN;
        } else if (type.equals(byte.class)) {
            return DEFAULT_BYTE;
        } else if (type.equals(short.class)) {
            return DEFAULT_SHORT;
        } else if (type.equals(int.class)) {
            return DEFAULT_INT;
        } else if (type.equals(long.class)) {
            return DEFAULT_LONG;
        } else if (type.equals(float.class)) {
            return DEFAULT_FLOAT;
        } else if (type.equals(double.class)) {
            return DEFAULT_DOUBLE;
        } else {
            // must be an Object type
            return null;
        }
    }
}
