package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin<T> {
    public T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x > 0;
        return returnValueByPredicate(value, comparator, predicate);
    }

    public T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = x -> x < 0;
        return returnValueByPredicate(value, comparator, predicate);
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

    private T returnValueByPredicate(List<T> list, Comparator<T> comparator, Predicate<Integer> predicate) {
        T min = null;
        T max = null;
        T result;
        if (list.isEmpty()) {
            result = null;
        } else {
            if (list.size() == 1) {
                min = list.get(0);
                max = list.get(0);
            } else {
                for (int i = 0; i < list.size() - 1; i++) {
                    if (min == null && max == null) {
                        min = list.get(i);
                        max = list.get(i);
                    }
                    if (comparator.compare(list.get(i), list.get(i + 1)) > 0) {
                        if (comparator.compare(list.get(i), max) > 0) {
                            max = list.get(i);
                        }
                        if (comparator.compare(list.get(i + 1), min) < 0) {
                            min = list.get(i + 1);
                        }
                    } else if (comparator.compare(list.get(i), list.get(i + 1)) < 0) {
                        if (comparator.compare(list.get(i), min) < 0) {
                            min = list.get(i);
                        }
                        if (comparator.compare(list.get(i + 1), max) > 0) {
                            max = list.get(i + 1);
                        }
                    }
                }
            }
            if (predicate.test(comparator.compare(min, max))) {
                result = min;
            } else {
                result = max;
            }
        }
        return result;
    }
}

