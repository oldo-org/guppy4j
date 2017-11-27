package org.guppy4j.reflect;

import org.guppy4j.function.QuadConsumer;
import org.guppy4j.function.QuadFunction;
import org.guppy4j.function.TriConsumer;
import org.guppy4j.function.TriFunction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Methods of a type T for given method reference
 */
public interface Methods<T> {

    Method<T> get(Consumer<T> consumer);

    <P> Method<T> get(BiConsumer<T, P> biConsumer);

    <P1, P2> Method<T> get(TriConsumer<T, P1, P2> triConsumer);

    <P1, P2, P3> Method<T> get(QuadConsumer<T, P1, P2, P3> quadConsumer);

    <R> Method<T> get(Function<T, R> function);

    <P, R> Method<T> get(BiFunction<T, P, R> biFunction);

    <P1, P2, R> Method<T> get(TriFunction<T, P1, P2, R> triFunction);

    <P1, P2, P3, R> Method<T> get(QuadFunction<R, T, P1, P2, P3> quadFunction);

}