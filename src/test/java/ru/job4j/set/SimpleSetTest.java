package ru.job4j.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddSeveralElementsIncludingDuplicates() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(22));
        assertTrue(set.add(333));
        assertTrue(set.contains(1));
        assertTrue(set.contains(22));
        assertTrue(set.contains(333));
        assertFalse(set.add(22));
        assertTrue(set.add(4444));
        assertFalse(set.add(4444));
        assertTrue(set.contains(4444));
    }
}