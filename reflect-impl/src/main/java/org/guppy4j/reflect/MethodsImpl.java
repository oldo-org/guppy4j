package org.guppy4j.reflect;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.guppy4j.function.QuadConsumer;
import org.guppy4j.function.QuadFunction;
import org.guppy4j.function.TriConsumer;
import org.guppy4j.function.TriFunction;

import java.lang.reflect.Modifier;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static net.bytebuddy.matcher.ElementMatchers.any;

/**
 * Converts given method references to Method object (type-safe reflection)
 */
public final class MethodsImpl<T> implements Methods<T> {

    private final Class<T> type;
    private final T proxy;
    private final MethodCapturer mc = new MethodCapturer();

    public MethodsImpl(Class<T> type) {
        try {
            if ((type.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                // TODO
                throw new IllegalArgumentException("Final classes are not supported yet");
            } else {
                proxy = new ByteBuddy()
                        .subclass(type)
                        .method(any()).intercept(InvocationHandlerAdapter.of(mc))
                        .make().load(type.getClassLoader()).getLoaded()
                        .newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        this.type = type;
    }

    @Override
    public Method<T> get(Consumer<T> consumer) {
        return getMethod(consumer);
    }

    @Override
    public <P> Method<T> get(BiConsumer<T, P> biConsumer) {
        return getMethod(biConsumer);
    }

    @Override
    public <P1, P2> Method<T> get(TriConsumer<T, P1, P2> triConsumer) {
        return getMethod(triConsumer);
    }

    @Override
    public <P1, P2, P3> Method<T> get(QuadConsumer<T, P1, P2, P3> quadConsumer) {
        return getMethod(quadConsumer);
    }

    @Override
    public <R> Method<T> get(Function<T, R> function) {
        return getMethod(function::apply);
    }

    @Override
    public <P, R> Method<T> get(BiFunction<T, P, R> biFunction) {
        return getMethod(biFunction::apply);
    }

    @Override
    public <P1, P2, R> Method<T> get(TriFunction<T, P1, P2, R> triFunction) {
        return getMethod(triFunction::apply);
    }

    @Override
    public <P1, P2, P3, R> Method<T> get(QuadFunction<T, P1, P2, P3, R> quadFunction) {
        return getMethod(quadFunction::apply);
    }

    private <P> Method<T> getMethod(BiConsumer<T, P> biConsumer) {
        return getMethod(getConsumer(biConsumer));
    }

    private <P1, P2> Method<T> getMethod(TriConsumer<T, P1, P2> triConsumer) {
        return getMethod(getBiConsumer(triConsumer));
    }

    private <P1, P2, P3> Method<T> getMethod(QuadConsumer<T, P1, P2, P3> quadConsumer) {
        return getMethod(getTriConsumer(quadConsumer));
    }

    private Method<T> getMethod(Consumer<T> consumer) {
        consumer.accept(proxy);
        return new MethodImpl<>(mc.get(), type);
    }

    private static <T, P1, P2, P3> TriConsumer<T, P1, P2> getTriConsumer(
            QuadConsumer<T, P1, P2, P3> quadConsumer) {
        return (T t, P1 p1, P2 p2) -> tryAllParameterTypes((P3 p3) -> quadConsumer.accept(t, p1, p2, p3));
    }

    private static <T, P1, P2> BiConsumer<T, P1> getBiConsumer(
            TriConsumer<T, P1, P2> triConsumer) {
        return (T t, P1 p1) -> tryAllParameterTypes((P2 p2) -> triConsumer.accept(t, p1, p2));
    }

    private static <T, P1> Consumer<T> getConsumer(BiConsumer<T, P1> biConsumer) {
        return t -> tryAllParameterTypes((P1 p1) -> biConsumer.accept(t, p1));
    }

    private static <P> void tryAllParameterTypes(Consumer<P> consumer) {
        try {
            consumer.accept(null);
        } catch (NullPointerException e) {
            for (Class<?> p : primitiveTypes) {
                try {
                    consumer.accept((P) DefaultValue.forType(p));
                    return;
                } catch (ClassCastException cce) {
                    // continue
                }
            }
        }
    }

    private static final Class<?>[] primitiveTypes = new Class<?>[]{
            int.class, boolean.class, double.class, long.class,
            char.class, byte.class, float.class, short.class
    };

}
