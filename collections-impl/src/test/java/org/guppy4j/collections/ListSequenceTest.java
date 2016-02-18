package org.guppy4j.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Tests ListSequence
 */
public class ListSequenceTest {

    private final Sequence<String> sequence = new ListSequence<>("a", "b", "c");

    @Test
    public void testPrevious() {
        assertNull("previous() should return null for the first item", sequence.previous("a"));
        final String message = "previous() should return the previous item in the sequence";
        assertEquals(message, "a", sequence.previous("b"));
        assertEquals(message, "b", sequence.previous("c"));
    }

    @Test
    public void testNext() {
        final String message = "next() should return the next item in the sequence";
        assertEquals(message, "b", sequence.next("a"));
        assertEquals(message, "c", sequence.next("b"));
        assertNull("next() should return null for last item", sequence.next("c"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPreviousRejectsAnyArgumentNotInTheSequence() {
        sequence.previous("x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNextRejectsAnyArgumentNotInTheSequence() {
        sequence.next("x");
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
