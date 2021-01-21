package org.oldo.reflect;

import org.oldo.function.BiConsumer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Methods class
 */
public final class MethodsViaProxyTest {

    public interface I {
        void m1();
        int m2();
        void m3(double p);
        int m4(int p, boolean b, String s);
        int m5(Integer p);
        Object m6(Float p, double c);
        void m7(Boolean b, boolean... booleans);
    }

    @Test
    public void testInterface() {
        final MethodsViaProxy<I> methods = new MethodsViaProxy<I>(I.class, new NativeProxyBuilder<>());
        assertEquals("m1", methods.get(I::m1).name());
        assertEquals("m2", methods.get(I::m2).name());
        assertEquals("m3", methods.get(I::m3).name());
        assertEquals("m4", methods.get(I::m4).name());
        assertEquals("m5", methods.get(I::m5).name());
        assertEquals("m6", methods.get(I::m6).name());
        final BiConsumer<I, Boolean> m7 = I::m7;
        assertEquals("m7", methods.get(m7).name());
    }

    public static class C {
        void m1() {
        }

        int m2() {
            return 0;
        }

        void m3(double p) {
        }

        char m4(int p, boolean b, String s) {
            return ' ';
        }

        long m5(Integer p) {
            return 0L;
        }

        Object m6(Float p, char c) {
            return new Object();
        }

        void m7(Boolean b, boolean... booleans) {
        }
    }

    @Test
    public void testClass() {
        final MethodsViaProxy<C> methods = new MethodsViaProxy<>(C.class, new ByteBuddyProxyBuilder<>());
        assertEquals("m1", methods.get(C::m1).name());
        assertEquals("m2", methods.get(C::m2).name());
        assertEquals("m3", methods.get(C::m3).name());
        assertEquals("m4", methods.get(C::m4).name());
        assertEquals("m5", methods.get(C::m5).name());
        assertEquals("m6", methods.get(C::m6).name());
        final BiConsumer<C, Boolean> m7 = C::m7;
        assertEquals("m7", methods.get(m7).name());  
    }
}
