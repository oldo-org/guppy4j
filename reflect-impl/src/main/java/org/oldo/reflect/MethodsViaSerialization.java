package org.oldo.reflect;

import org.oldo.function.BiConsumer;
import org.oldo.function.BiFunction;
import org.oldo.function.Consumer;
import org.oldo.function.Function;
import org.oldo.function.QuadConsumer;
import org.oldo.function.QuadFunction;
import org.oldo.function.TriConsumer;
import org.oldo.function.TriFunction;

/**
 * Methods reflection using serialization
 */
public final class MethodsViaSerialization<T> implements Methods<T> {

    private final Class<T> type;

    public MethodsViaSerialization(Class<T> type) {
        this.type = type;
    }

    public Method<T> get(Consumer<T> consumer) {
        return method(consumer);
    }

    public <P1> Method<T> get(BiConsumer<T, P1> consumer) {
        return method(consumer);
    }

    @Override
    public <P1, P2> Method<T> get(TriConsumer<T, P1, P2> triConsumer) {
        return method(triConsumer);
    }

    @Override
    public <P1, P2, P3> Method<T> get(QuadConsumer<T, P1, P2, P3> quadConsumer) {
        return null;
    }

    public <R> Method<T> get(Function<T, R> function) {
        return method(function);
    }

    public <P1, R> Method<T> get(BiFunction<T, P1, R> function) {
        return method(function);
    }

    public <P1, P2, R> Method<T> get(TriFunction<T, P1, P2, R> function) {
        return method(function);
    }

    public <P1, P2, P3, R> Method<T> get(QuadFunction<T, P1, P2, P3, R> function) {
        return method(function);
    }

    private Method<T> method(MethodFinder lambda) {
        return new MethodImpl<>(lambda.method(), type);
    }
}
