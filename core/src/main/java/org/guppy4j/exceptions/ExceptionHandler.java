package org.guppy4j.exceptions;

/**
 * TODO: Document this!
 */
public interface ExceptionHandler<E extends Exception> {

    void tryUnchecked(ActionToTry<E> action);

    <P, R> R tryUnchecked(FunctionToTry<P, R, E> function, P parameter);
}
