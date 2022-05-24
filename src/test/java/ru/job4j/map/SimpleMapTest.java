package ru.job4j.map;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddThenControlAddedValue() {
        Map<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1111));
        assertEquals((Integer) 1111, map.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddThenRemove() {
        Map<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(2, 1111));
        assertEquals((Integer) 1111, map.get(2));
        map.remove(2);
        assertNull(map.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddThenRemoveTwoTimesByTheSameIndex() {
        Map<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1111));
        assertTrue(map.put(2, 2222));
        assertEquals((Integer) 2222, map.get(2));
        map.remove(2);
        assertNull(map.get(2));
        map.remove(2);
        assertNull(map.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddThenRemoveTwoTimes() {
        Map<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1111));
        assertTrue(map.put(2, 2222));
        assertEquals((Integer) 1111, map.get(1));
        assertEquals((Integer) 2222, map.get(2));
        map.remove(2);
        assertNull(map.get(2));
        map.remove(1);
        assertNull(map.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void whenTableExpanding() {
        Map<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(12, 9999));
        assertEquals((Integer) 9999, map.get(3));
        assertTrue(map.put(1, 1111));
        assertTrue(map.put(2, 1111));
        assertTrue(map.put(3, 1111));
        assertEquals((Integer) 1111, map.get(3));
        assertTrue(map.put(4, 1111));
        assertTrue(map.put(5, 1111));
        assertTrue(map.put(6, 1111));
        assertTrue(map.put(7, 1111));
        assertTrue(map.put(8, 1111));
        assertTrue(map.put(9, 1111));
        assertTrue(map.put(10, 1111));
        assertTrue(map.put(11, 1111));
        assertTrue(map.put(12, 9999));
        assertEquals((Integer) 9999, map.get(12));
    }
}
