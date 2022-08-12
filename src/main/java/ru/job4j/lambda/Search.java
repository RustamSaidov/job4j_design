package ru.job4j.lambda;

/* import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

Плохой код по принципу DRY, YAGNI:
public class Search {

    static List<File> findByMask(String mask) {
        List<File> rsl = new ArrayList<>();
        for (File file : ..) {
            if (Pattern.matches(mask, file.getName())) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    static List<File> findByName(String name) {
        List<File> rsl = new ArrayList<>();
        for (File file : ..) {
            if (name.equals(file.getName())) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    static List<File> findByExt(String ext) {
        List<File> rsl = new ArrayList<>();
        for (File file : ..) {
            if (file.getName().endWiths(ext)) {
                rsl.add(file);
            }
        }
        return rsl;
    }
}

Хороший код по принципу DRY, YAGNI:


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {

    static List<File> findBy(Predicate<File> predicate) {
        List<File> rsl = new ArrayList<>();
        for (File file : ..) {
            if (predicate.test(file)) {
                rsl.add(file);
            }
        }
        return rsl;
    }
    static List<File> findByMask(String mask) {
        return findBy(file -> Pattern.matches(mask, file.getName()));
    }

    static List<File> findByName(String name) {
        return findBy(file -> file.getName().equals(name));
    }

    static List<File> findByExt(String ext) {
        return findBy(file -> file.getName().endsWith(ext));
    }
} */