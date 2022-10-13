package ru.job4j.stream;

import java.util.stream.Stream;

public class ForEachMethod {
    public static void show(Stream<Integer> data) {
        data.forEach(n-> System.out.println(n));
        /*Альтернативная версия:
        data.forEach(System.out::println);
         */
    }
}