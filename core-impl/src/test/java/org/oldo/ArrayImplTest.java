package org.oldo;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests ArrayImpl
 */
public class ArrayImplTest {

    @Test
    public void test() {
        final Array<String> array = new ArrayImpl<>(String[]::new, "a", "b", "c");
        final Array<String> derivedArray = array.append("d", "e", "f");
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f"}, derivedArray.content());
    }
}
