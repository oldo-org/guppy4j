package org.oldo.exceptions;

/**
 * Calls an action or function and handles checked exceptions of type E,
 * so that the original caller does not have to handle those exceptions.
 */
public interface ExceptionHandler<E extends Exception> {

    void tryUnchecked(ActionToTry<E> action);

    <P, R> R tryUnchecked(FunctionToTry<P, R, E> function, P parameter);

}
