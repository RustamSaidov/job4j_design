package ru.job4j.cache;

import ru.job4j.io.PrintFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Emulator {
    private static final int CACHING_FILE = 1;
    private static final int READ_FILE_CACHE = 2;

    private static final String START_MENU = """
            Введите путь директории из которой должны быть прочитаны файлы в кеш. 
            Текстовые файлы лежат например в папке: 
            C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\gc\\leak\\files """;

    private static final String CHOICE_MENU = """
            Введите 1 для кеширования файла.
            Введите 2, для чтения файла из кеша.
            Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        int choice = 1;
        String fileName;

        System.out.println(START_MENU);
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = new DirFileCache(scanner.nextLine());
        PrintFiles printFiles = new PrintFiles();

        while (choice == CACHING_FILE || choice == READ_FILE_CACHE) {
            System.out.println("Вы находитесь в директории " + dirFileCache.getCachingDir() + ". ");
            System.out.println("Директория содержит следующие текстовые файлы: ");
            Files.walkFileTree(Path.of(dirFileCache.getCachingDir()), printFiles);
            System.out.println(CHOICE_MENU);
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == CACHING_FILE) {
                System.out.println("Введите название файла для кеширования:");
                fileName = scanner.nextLine();
                dirFileCache.get(fileName);

            } else if (choice == READ_FILE_CACHE) {
                System.out.println("Введите название файла, который желаете прочитать из кеша:");
                fileName = scanner.nextLine();
                System.out.println("Содержимое файла " + fileName + " следующее:");
                System.out.println(dirFileCache.get(fileName));
            }
        }
    }
}