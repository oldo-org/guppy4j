package org.guppy4j.exceptions;

import java.util.function.Function;

/**
 * Attempts to execute functions or actions that can throw a certain checked exception type E,
 * handles those exceptions and converts them to an unchecked exception
 */
public final class ExceptionHandler<E extends Exception> {

    private final Class<E> exType;
    private final Function<E, RuntimeException> exConverter;

    public ExceptionHandler(Class<E> exType, Function<E, RuntimeException> exConverter) {
        if (RuntimeException.class.isAssignableFrom(exType)) {
            throw new IllegalArgumentException(exType + " must be a checked exception type");
        }
        this.exType = exType;
        this.exConverter = exConverter;
    }

    /**
     * Executes the action, catches any checked exception of type E,
     * converts (usually wraps) it to an RE and throws that.
     * Any other
     *
     * @param action The action to execute that might throw an E
     */
    public void tryUnchecked(ActionToTry<E> action) {
        tryUnchecked(noParam -> {
            action.execute();
            return null;
        }, null);
    }

    public <P, R> R tryUnchecked(FunctionToTry<P, R, E> function, P parameter) {
        try {
            return function.apply(parameter);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            if (exType.isInstance(e)) {
                throw exConverter.apply(exType.cast(e));
            } else {
                throw new IllegalStateException("Unexpected checked exception", e);
            }
        }
    }
}
