package ru.job4j.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class OptionalGetAndIsPresent {

    public static int get(int[] data, int el) {
        Optional optional = indexOf(data, el);
        return optional.isPresent() ? (int) optional.get() : -1;
    }

    private static Optional<Integer> indexOf(int[] data, int el) {
        List<Integer> list = Arrays.stream( data ).boxed().collect( Collectors.toList() );
        return list.contains(el) ? Optional.of(list.indexOf(el)) : Optional.empty();
    }
}

