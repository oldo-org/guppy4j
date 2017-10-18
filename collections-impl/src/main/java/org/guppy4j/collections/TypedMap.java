package org.guppy4j.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Stores objects by their type
 */
public class TypedMap {

	private final ConcurrentMap<Class<?>, Object> map = new ConcurrentHashMap<>();

	public void put(Object object) {
		map.put(object.getClass(), object);
	}

	public <T> T get(Class<T> c) {
		return (T) map.get(c);
	}

}
