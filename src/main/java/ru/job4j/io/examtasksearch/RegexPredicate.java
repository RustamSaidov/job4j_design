package ru.job4j.io.examtasksearch;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.job4j.io.examtasksearch.SearchFileByCriteria.names;

public class RegexPredicate<T> implements Predicate<T> {
    String regex = names.getValues().get("n");
    Pattern pattern = Pattern.compile(regex);

    public boolean test(T varc) {
        Path path = (Path) varc;
        Matcher matcher = pattern.matcher(path.getFileName().toString());
        return matcher.matches();
    }
}
