package ru.job4j.io.examtasksearch;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ExamTaskMain {
    static ArgsName names = new ArgsName();
    static Search searchobj = new Search();

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (int i = 0; i < sources.size(); i++) {
                zip.putNextEntry(new ZipEntry(sources.get(i).toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(sources.get(i).toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            System.out.println("mask variant");
        }

        //Фильтр по регулярному выражению:
        if (names.getValues().get("t").equals("regex")) {
            listpath = searchobj.search(start, p -> !p.toFile().getName().matches(names.getValues().get("n")));
        }

        //List<Path> listpath = searchobj.search(start, p -> !p.toFile().getName().endsWith(names.getValues().get("e")));
//        Zip zip = new Zip();
//        zip.packFiles(listpath, new File(names.getValues().get("o")));
        System.out.println(listpath);
        List<Path> listp = new ArrayList<>();
        listp.add(Path.of("some.txt"));
        listp.add(Path.of("some2.txt"));
        save(listpath, "listOfThePaths.txt");
        save(listp, "listOfTheP.txt");
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

