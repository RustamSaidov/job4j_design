package ru.job4j.stream;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlatIt {

    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        return iteratorToStream(it).flatMap(n -> iteratorToStream(n)).collect(Collectors.toList());
    }

    private static <T> Stream<T> iteratorToStream(Iterator<T> it) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED),
                false);
    }
}