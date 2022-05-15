package ru.job4j.iterator;

import java.util.function.Predicate;

public class SamplePredicate<T> implements Predicate<T> {
    T varc1;

    public boolean test(T varc) {
        if (varc1.equals(varc)) {
            return true;
        }
        return false;
    }
}
