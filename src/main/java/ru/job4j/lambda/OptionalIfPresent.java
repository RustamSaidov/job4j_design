package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Optional;

public class OptionalIfPresent {

    public static void ifPresent(int[] data) {
        OptionalIfPresent.max(data).ifPresent((x)->System.out.println("Max: "+OptionalIfPresent.max(data).get()));
    }

    private static Optional<Integer> max(int[] data) {
        return data.length==0 ? Optional.empty() : Optional.of(Arrays.stream(data).max().getAsInt());
    }
}