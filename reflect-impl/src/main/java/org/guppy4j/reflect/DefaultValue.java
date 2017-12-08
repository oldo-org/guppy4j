package org.guppy4j.reflect;

/**
 * Default value for each of the known Java type
 */
public final class DefaultValue {

    // These gets initialized to their default values
    private static boolean defaultBoolean;
    private static byte defaultByte;
    private static char defaultChar;
    private static short defaultShort;
    private static int defaultInt;
    private static long defaultLong;
    private static float defaultFloat;
    private static double defaultDouble;

    public static Object forType(Class<?> type) {
        if (type.equals(boolean.class)) {
            return defaultBoolean;
        } else if (type.equals(char.class)) {
            return defaultChar;
        } else if (type.equals(byte.class)) {
            return defaultByte;
        } else if (type.equals(short.class)) {
            return defaultShort;
        } else if (type.equals(int.class)) {
            return defaultInt;
        } else if (type.equals(long.class)) {
            return defaultLong;
        } else if (type.equals(float.class)) {
            return defaultFloat;
        } else if (type.equals(double.class)) {
            return defaultDouble;
        } else {
            // must be an Object type
            return null;
        }
    }
}
