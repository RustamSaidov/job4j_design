package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\projects\\job4j_design\\data"), duplicatesVisitor);
        duplicatesVisitor.tempFPList.forEach(System.out::println);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        duplicatesVisitor.dublicateSet.forEach(System.out::println);
        System.out.println(duplicatesVisitor.tempFPList.size());
        System.out.println(duplicatesVisitor.dublicateSet.size());
    }
}
