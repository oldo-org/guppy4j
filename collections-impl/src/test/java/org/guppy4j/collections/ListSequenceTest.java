package org.guppy4j.collections;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the ListSequence implementation of Sequence<T>
 */
public class ListSequenceTest {

    private final Sequence<String> chars = new ListSequence<>("a", "b", "c");

    @Test
    public void testPrevious() {
        final String message = "previous() should return the previous item in the sequence";
        assertEquals(message, "a", chars.previous("b"));
        assertEquals(message, "b", chars.previous("c"));
    }

    @Test
    public void testNext() {
        final String message = "next() should return the next item in the sequence";
        assertEquals(message, "b", chars.next("a"));
        assertEquals(message, "c", chars.next("b"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNoPreviousForFirstItem() {
        chars.previous("a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNoNextForLastItem() {
        chars.next("c");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPreviousRejectsAnyArgumentNotInTheSequence() {
        chars.previous("x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextRejectsAnyArgumentNotInTheSequence() {
        chars.next("x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullsRejected() {
        new ListSequence<>(null, "a", "b", "c", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDuplicateObjectsRejected() {
        new ListSequence<>("a", "b", "c", "c");
    }

}
