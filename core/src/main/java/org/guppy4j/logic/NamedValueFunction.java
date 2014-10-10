package org.guppy4j.logic;

/**
 * A function that takes the name of the parameter
 */
public interface NamedValueFunction<X, T> extends Function2<String, X, T> {

    T result(String name, X value);

}
