package ru.job4j.stream;

import java.util.List;
import java.util.Optional;

public class OptionalFlatMap {
    public static Optional<Integer> flatMap(List<String> strings) {
        return strings.stream()
                .filter(n -> n.endsWith("java"))
                .findFirst()
                .flatMap(n -> Optional.of(n.length()));
    }
}