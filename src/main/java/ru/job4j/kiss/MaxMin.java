package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class MaxMin<T> {
    public T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (x, y) -> comparator.compare(x, y) > 0;
        return returnValueByPredicate(value, predicate);
    }

    public T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (x, y) -> comparator.compare(x, y) < 0;
        return returnValueByPredicate(value, predicate);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("BBB");
        list.add("DDD");
        list.add("AAA");
        list.add("DDD");
        list.add("FFF");
        list.add("MMM");
        list.add("EEE");
        list.add("CCC");

        Comparator comparator = new StringComparator();

        MaxMin maxMin = new MaxMin();
        System.out.println("max" + maxMin.max(list, comparator));
        System.out.println("min" + maxMin.min(list, comparator));
    }

    private T returnValueByPredicate(List<T> list, BiPredicate<T, T> predicate) {
        T result = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            if (predicate.test(list.get(i), result)) {
                result = list.get(i);
            }
        }
        return result;
    }
}