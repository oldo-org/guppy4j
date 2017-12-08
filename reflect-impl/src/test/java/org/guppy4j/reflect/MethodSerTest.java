package org.guppy4j.reflect;

import org.junit.Test;

import java.lang.invoke.SerializedLambda;

import static org.junit.Assert.assertEquals;

/**
 * TODO: Document this!
 */
public final class MethodSerTest {

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
        final MethodsSer<I> methodsSer = new MethodsSer<>(I.class);

        final SerializedLambda m2 = methodsSer.get(I::m2);
        assertEquals("m2", m2.getImplMethodName());
        assertEquals(I.class.getName(), m2.getImplClass().replace('/', '.'));

        final SerializedLambda m3 = methodsSer.get(I::m3);
        assertEquals("m3", m3.getImplMethodName());
        assertEquals(I.class.getName(), m3.getImplClass().replace('/', '.'));

        final SerializedLambda m4 = methodsSer.get(I::m4);
        assertEquals("m4", m4.getImplMethodName());
        assertEquals(I.class.getName(), m4.getImplClass().replace('/', '.'));

        final SerializedLambda m6 = methodsSer.get(I::m6);
        assertEquals("m6", m6.getImplMethodName());
        assertEquals(I.class.getName(), m6.getImplClass().replace('/', '.'));
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
        final MethodsSer<C> methods = new MethodsSer<>(C.class);
        assertEquals("x", methods.get(C::x).getImplMethodName());

        final MethodsSer<Object> obj = new MethodsSer<>(Object.class);
        assertEquals("y", obj.get(C::y).getImplMethodName());
    }
}
