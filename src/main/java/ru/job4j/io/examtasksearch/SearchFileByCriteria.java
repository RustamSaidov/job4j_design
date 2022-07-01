package ru.job4j.io.examtasksearch;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class SearchFileByCriteria {
    static ArgsName names = new ArgsName();
    static Search searchobj = new Search();

    public static void main(String[] args) throws IOException {
        names = ArgsName.of(args);
        searchobj.argumentsValidation(names.getValues().values().toArray(new String[0]));
        Path start = Path.of(names.getValues().get("d"));
        List<Path> listpath = new ArrayList<>();

        //Фильтр по полному совпадению имени:
        if (names.getValues().get("t").equals("name")) {
            listpath = searchobj.search(start, p -> p.toFile().getName().equals(names.getValues().get("n")));
        }

        //Фильтр по маске:
        if (names.getValues().get("t").equals("mask")) {
            MaskPredicate<Path> filter = new MaskPredicate<>();
            listpath = searchobj.search(start, filter);
        }

        //Фильтр по регулярному выражению:
        if (names.getValues().get("t").equals("regex")) {
            RegexPredicate<Path> filter = new RegexPredicate<>();
            listpath = searchobj.search(start, filter);
        }
        save(listpath, "listOfThePaths.txt");
    }

    public static void save(List<Path> listpath, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (int i = 0; i < listpath.size(); i++) {
                out.println(listpath.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

