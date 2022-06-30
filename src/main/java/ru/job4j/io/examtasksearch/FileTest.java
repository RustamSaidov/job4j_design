package ru.job4j.io.examtasksearch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class FileTest {

    public static void matches(Path path, String glob){
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
        System.out.println(matcher.matches(path));
    }
    public static void main(String[] args) throws IOException {
        //Path path = Paths.get("\\com\\java\\One.java");
        Path path = Paths.get("something.java");
        matches(path, "glob:*.java");
        matches(path, "glob:**\\*.java");
        matches(path, "glob:*");
        matches(path, "glob:**");
        matches(path, "glob:*.*");
        matches(path, "glob:*.*?");
        matches(path, "glob:*.*????");
        matches(path, "glob:*.*?????");
        matches(path, "glob:*.*av???");
        matches(path, "glob:*.*av?");

    }
}
