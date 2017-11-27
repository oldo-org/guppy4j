package org.guppy4j.reflect;

import java.lang.reflect.Array;

/**
 * Default value for each of the known Java type
 */
public final class DefaultValue {

    public static Object forType(Class<?> type) {
        return Array.get(Array.newInstance(type, 1), 0);
    }
}
