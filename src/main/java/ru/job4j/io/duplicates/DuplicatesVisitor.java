package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, Path> tempFPmap = new HashMap<>();
    Set<Path> dublicateSet = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (tempFPmap.containsKey(fileProperty)) {
            dublicateSet.add(tempFPmap.get(fileProperty));
            dublicateSet.add(file.toAbsolutePath());
        } else {
            tempFPmap.put(fileProperty, file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
