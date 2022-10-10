package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;

public class MinMethod {
    public static String min(List<String> list) {
        /*/вариант исполнения:
        return list.stream().min(Comparator.comparingInt(String::length)).get();
        */
        return list.stream().min((x1, x2) -> Integer.compare(x1.length(), x2.length())).get();
    }
}