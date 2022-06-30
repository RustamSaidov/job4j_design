package ru.job4j.io.examtasksearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        argumentsValidation(args);

        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    static void argumentsValidation(String[] args) {
        argumentTransferValidation(args);
        folderAdressValidatoin(args);
        fileExtentionValidation(args);
    }

    /*Проверка того, что в аргументе было передано нужное расширение файла*/
    private static void fileExtentionValidation(String[] args) {
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Wrong file extension for search. Change the file extension search "
                    + "argument according to the search conditions.");
        }
    }

    /*Проверка того, что в аргументе был передан нужный адрес папки для проверки файлов*/
    private static void folderAdressValidatoin(String[] args) {
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder %s Not exist ", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Folder %s Not directory ", file.getAbsoluteFile()));
        }
    }

    /*Проверка того, что были переданы аргументы коммандной строки*/
    private static void argumentTransferValidation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }


}

class SearchFiles implements FileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> listPath = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;

    }

    public List<Path> getPaths() {
        return listPath;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            listPath.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}

