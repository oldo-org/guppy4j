package org.guppy4j.reflect;

import org.guppy4j.function.QuadFunction;
import org.guppy4j.function.TriFunction;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * TODO: Document this!
 */
public final class MethodsSer<T> {

    private final Class<T> type;

    public MethodsSer(Class<T> type) {
        this.type = type;
    }

    public interface SerConsumer<T> extends Consumer<T>, Serializable {
    }

    public interface SerBiConsumer<T, P1> extends BiConsumer<T, P1>, Serializable {
    }

    public interface SerFunction<T, R> extends Function<T, R>, Serializable {
    }

    public interface SerBiFunction<T, P1, R> extends BiFunction<T, P1, R>, Serializable {
    }

    public interface SerTriFunction<T, P1, P2, R> extends TriFunction<T, P1, P2, R>, Serializable {
    }

    public interface SerQuadFunction<T, P1, P2, P3, R> extends QuadFunction<T, P1, P2, P3, R>, Serializable {
    }


    public SerializedLambda get(SerConsumer<T> consumer) {
        return serialize(consumer);
    }

    public <P1> SerializedLambda get(SerBiConsumer<T, P1> consumer) {
        return serialize(consumer);
    }

    public <R> SerializedLambda get(SerFunction<T, R> function) {
        return serialize(function);
    }

    public <P1, R> SerializedLambda get(SerBiFunction<T, P1, R> function) {
        return serialize(function);
    }

    public <P1, P2, R> SerializedLambda get(SerTriFunction<T, P1, P2, R> function) {
        return serialize(function);
    }

    public <P1, P2, P3, R> SerializedLambda get(SerQuadFunction<T, P1, P2, P3, R> function) {
        return serialize(function);
    }

    private SerializedLambda serialize(Serializable lambda) {
        for (Class<?> c = lambda.getClass(); c != null; c = c.getSuperclass()) {
            try {
                final Method m = c.getDeclaredMethod("writeReplace");
                m.setAccessible(true);
                return SerializedLambda.class.cast(m.invoke(lambda));

            } catch (IllegalAccessException | InvocationTargetException | ClassCastException e) {
                break;
            } catch (NoSuchMethodException e) {
                // do nothing
            }
        }
        return null;
    }
}
