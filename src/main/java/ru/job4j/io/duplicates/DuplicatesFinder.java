package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    static DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();

    public static void filterDublicates() throws IOException {
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
    }

    public static void main(String[] args) throws IOException {
        filterDublicates();
        System.out.println("List of duplicate elements in directory:");
        duplicatesVisitor.dublicateSet.forEach(System.out::println);
        System.out.println("Number of duplicate elements in directory: " + duplicatesVisitor.dublicateSet.size());
    }
}
