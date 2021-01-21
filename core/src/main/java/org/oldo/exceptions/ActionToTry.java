package org.oldo.exceptions;

/**
 * An action that can throw exceptions of type E
 */
public interface ActionToTry<E extends Exception> {

    void execute() throws E;

}
