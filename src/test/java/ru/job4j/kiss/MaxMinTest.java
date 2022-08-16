package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMaxXXX() {
        List<String> list = new ArrayList<>();
        list.add("BBB");
        list.add("XXX");
        list.add("AAA");
        list.add("DDD");
        list.add("FFF");
        list.add("MMM");
        list.add("EEE");
        list.add("CCC");

        Comparator<String> comparator = new StringComparator();
        MaxMin<String> maxMin = new MaxMin<>();
        assertThat(maxMin.max(list, comparator), is("XXX"));
    }

    @Test
    public void whenMinAAA() {
        List<String> list = new ArrayList<>();
        list.add("BBB");
        list.add("XXX");
        list.add("AAA");
        list.add("DDD");
        list.add("FFF");
        list.add("MMM");
        list.add("EEE");
        list.add("CCC");

        Comparator<String> comparator = new StringComparator();
        MaxMin<String> maxMin = new MaxMin<>();
        assertThat(maxMin.min(list, comparator), is("AAA"));
    }

    @Test
    public void whenMinEqualsMax() {
        List<String> list = new ArrayList<>();
        list.add("DDD");
        list.add("DDD");
        list.add("DDD");

        Comparator<String> comparator = new StringComparator();
        MaxMin<String> maxMin = new MaxMin<>();
        assertThat(maxMin.max(list, comparator), is("DDD"));
        assertThat(maxMin.min(list, comparator), is("DDD"));
    }

    @Test
    public void whenListFromOneElement() {
        List<String> list = new ArrayList<>();
        list.add("DDD");

        Comparator<String> comparator = new StringComparator();
        MaxMin<String> maxMin = new MaxMin<>();
        assertThat(maxMin.max(list, comparator), is("DDD"));
        assertThat(maxMin.min(list, comparator), is("DDD"));
    }

    @Test
    public void whenListisEmpty() {
        List<String> list = new ArrayList<>();

        Comparator<String> comparator = new StringComparator();
        MaxMin<String> maxMin = new MaxMin<String>();
        assertNull(maxMin.max(list, comparator));
    }
}
