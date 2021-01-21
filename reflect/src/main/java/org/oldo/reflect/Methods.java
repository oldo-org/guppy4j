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

    <P1, P2, P3, R> Method<T> get(QuadFunction<T, P1, P2, P3, R> quadFunction);

}