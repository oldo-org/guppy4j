package org.guppy4j.reflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MethodsViaSerialization class
 */
public final class MethodsViaSerializationTest {

    public interface I {
        void m1();

        int m2();

        void m3(double p);

        int m4(int p, boolean b, String s);

        int m5(Integer p);

        Object m6(Float p, char c);

        void m7(Boolean b, boolean... booleans);
    }

    @Test
    public void testInterface() {
        final Methods<I> methods = new MethodsViaSerialization<>(I.class);

        final Method<I> m1 = methods.get(I::m1);
        assertEquals("m1", m1.getMethod().getName());
        assertEquals(I.class, m1.getDeclaringClass());

        final Method<I> m2 = methods.get(I::m2);
        assertEquals("m2", m2.getMethod().getName());
        assertEquals(I.class, m2.getDeclaringClass());

        final Method<I> m3 = methods.get(I::m3);
        assertEquals("m3", m3.getMethod().getName());
        assertEquals(I.class, m3.getDeclaringClass());

        final Method<I> m4 = methods.get(I::m4);
        assertEquals("m4", m4.getMethod().getName());
        assertEquals(I.class, m4.getDeclaringClass());

        final Method<I> m5 = methods.get(I::m5);
        assertEquals("m5", m5.getMethod().getName());
        assertEquals(I.class, m5.getDeclaringClass());

        final Method<I> m6 = methods.get(I::m6);
        assertEquals("m6", m6.getMethod().getName());
        assertEquals(I.class, m6.getDeclaringClass());

    }

    public static final class C {
        void x() {
        }

        static char y(Object o) {
            return ' ';
        }
    }

    @Test
    public void test() {
        final MethodsViaSerialization<C> methods = new MethodsViaSerialization<>(C.class);
        assertEquals("x", methods.get(C::x).getMethod().getName());

        final MethodsViaSerialization<Object> obj = new MethodsViaSerialization<>(Object.class);
        assertEquals("y", obj.get(C::y).getMethod().getName());
    }
}
