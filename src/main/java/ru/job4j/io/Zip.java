package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
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
        List<Path> listpath = searchobj.search(start, p -> !p.toFile().getName().endsWith(names.getValues().get("e")));
        Zip zip = new Zip();
        zip.packFiles(listpath, new File(names.getValues().get("o")));
    }
}
