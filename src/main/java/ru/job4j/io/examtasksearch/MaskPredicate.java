package ru.job4j.io.examtasksearch;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Predicate;

import static ru.job4j.io.examtasksearch.ExamTaskMain.names;

public class MaskPredicate<T> implements Predicate<T> {
    String str = "glob:"+names.getValues().get("n");
    PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(str);

    public boolean test(T varc) {
        Path path = (Path) varc;
        if (pathMatcher.matches(path.getFileName())) {
            return true;
        }
        return false;
    }
}
