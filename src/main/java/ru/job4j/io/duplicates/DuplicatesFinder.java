package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DuplicatesFinder {
    static DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();

    public static void filterDublicates() throws IOException {
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        List<FileProperty> list = duplicatesVisitor.tempFPList;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                int lastFileSepInd1 = list.get(i).getName().lastIndexOf('\\');
                int lastFileSepInd2 = list.get(j).getName().lastIndexOf('\\');
                if (list.get(i).getName().substring(lastFileSepInd1 + 1)
                        .equals(list.get(j).getName().substring(lastFileSepInd2 + 1))
                        && i != j
                        && list.get(i).getSize() == list.get(j).getSize()) {
                    duplicatesVisitor.dublicateSet.add(list.get(i));
                    duplicatesVisitor.dublicateSet.add(list.get(j));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        filterDublicates();
        System.out.println("List of elements in directory:");
        duplicatesVisitor.tempFPList.forEach(System.out::println);
        System.out.println("Number of elements in directory: " + duplicatesVisitor.tempFPList.size());
        System.out.println("List of duplicate elements in directory:");
        duplicatesVisitor.dublicateSet.forEach(System.out::println);
        System.out.println("Number of duplicate elements in directory: " + duplicatesVisitor.dublicateSet.size());
    }
}
