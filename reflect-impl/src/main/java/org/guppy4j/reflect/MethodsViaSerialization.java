package org.guppy4j.reflect;

import org.guppy4j.function.BiConsumer;
import org.guppy4j.function.BiFunction;
import org.guppy4j.function.Consumer;
import org.guppy4j.function.Function;
import org.guppy4j.function.QuadConsumer;
import org.guppy4j.function.QuadFunction;
import org.guppy4j.function.TriConsumer;
import org.guppy4j.function.TriFunction;

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
