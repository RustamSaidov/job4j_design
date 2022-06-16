package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    static ArgsName names = new ArgsName();
    static Search searchobj = new Search();

    public void packFiles(List<Path> sources, File target) {
        for (int i = 0; i < sources.size(); i++) {
            packSingleFile(sources.get(i).toFile(), target);
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*searchobj.argumentsValidation(args);*/
        names = ArgsName.of(args);
        Path start = Path.of(names.getValues().get("d"));
        List<Path> listpath = searchobj.search(start, p -> p.toFile().getName().endsWith(names.getValues().get("e")));
        Zip zip = new Zip();
        zip.packFiles(listpath, new File(names.getValues().get("o")));








/*
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
    }
}
