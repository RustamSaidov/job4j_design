package ru.job4j.lambda;

import java.util.Optional;

public class OptionalOrlElse {
    public static Integer orElse(Optional<Integer> optional) {
        /* do refactor
        Integer value = -1;
        if (optional.isPresent()) {
            value = optional.get();
        }
        return value;
        */
        return optional.orElse(-1);
    }
}