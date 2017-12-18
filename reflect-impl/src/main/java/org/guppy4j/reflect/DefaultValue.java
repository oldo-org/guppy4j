package org.guppy4j.reflect;

import java.util.HashMap;
import java.util.Map;

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

    private static Map<Class<?>, Object> primitives = new HashMap<>();

    static {
		primitives.put(boolean.class, defaultBoolean);
		primitives.put(char.class, defaultChar);
		primitives.put(byte.class, defaultByte);
		primitives.put(short.class, defaultShort);
		primitives.put(int.class, defaultInt);
		primitives.put(long.class, defaultLong);
		primitives.put(float.class, defaultFloat);
		primitives.put(double.class, defaultDouble);
	}

    public static Object forType(Class<?> type) {
    	return primitives.get(type);
    }

	public static Iterable<Object> forPrimitiveTypes() {
		return primitives.values();
	}
}
