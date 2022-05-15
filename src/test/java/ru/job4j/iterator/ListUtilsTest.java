package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterTwoTimesLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
        ListUtils.addAfter(input, 3, 44);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 44)));
    }

    @Test
    public void whenAddAfterTwoTimesIntoTheSameIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
        ListUtils.addAfter(input, 2, 33);
        assertThat(input, is(Arrays.asList(0, 1, 2, 33, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemoveAllOneElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeAll(input, Arrays.asList(0));
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenRemoveAllSeveralMateElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeAll(input, Arrays.asList(2, 3));
        assertThat(input, is(Arrays.asList(0, 1)));
    }

    @Test
    public void whenRemoveAllSeveralElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.removeAll(input, Arrays.asList(0, 3));
        assertThat(input, is(Arrays.asList(1, 2, 4)));
    }

    @Test
    public void whenRemoveIfOneElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        SamplePredicate<Integer> filter = new SamplePredicate<>();
        filter.varc1 = 2;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(0, 1, 3)));
    }

    @Test
    public void whenRemoveIfSeveralElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4));
        SamplePredicate<Integer> filter = new SamplePredicate<>();
        filter.varc1 = 2;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(0, 1, 3, 4)));
    }

    @Test
    public void whenReplaceIfOneElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        SamplePredicate<Integer> filter = new SamplePredicate<>();
        filter.varc1 = 2;
        ListUtils.replaceIf(input, filter, 222);
        assertThat(input, is(Arrays.asList(0, 1, 222, 3)));
    }

    @Test
    public void whenReplaceIfSeveralElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 2, 4));
        SamplePredicate<Integer> filter = new SamplePredicate<>();
        filter.varc1 = 2;
        ListUtils.replaceIf(input, filter, 222);
        assertThat(input, is(Arrays.asList(0, 1, 222, 3, 222, 4)));
    }

    @Test
    public void whenAddBeforeAddAfterRemoveIfReplaseIfRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.addAfter(input, 3, 4);
        ListUtils.addAfter(input, 4, 5);
        ListUtils.addAfter(input, 5, 6);
        ListUtils.addAfter(input, 6, 7);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(0, 2, 1, 2, 3, 4, 5, 6, 7)));
        SamplePredicate<Integer> filter = new SamplePredicate<>();
        filter.varc1 = 2;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(0, 1, 3, 4, 5, 6, 7)));
        filter.varc1 = 0;
        ListUtils.replaceIf(input, filter, 1);
        assertThat(input, is(Arrays.asList(1, 1, 3, 4, 5, 6, 7)));
        ListUtils.removeAll(input, Arrays.asList(1, 7));
        assertThat(input, is(Arrays.asList(3, 4, 5, 6)));
    }
}