package org.guppy4j.exceptions;

/**
 * A function that can throw exceptions of type E
 */
public interface FunctionToTry<P, R, E extends Exception> {

    R apply(P parameter) throws E;

}
