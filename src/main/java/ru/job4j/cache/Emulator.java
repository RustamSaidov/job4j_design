package ru.job4j.cache;

import ru.job4j.io.PrintFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Emulator {
    private static int choice = 1;
    private static String fileName;

    public static void main(String[] args) throws IOException {

        System.out.println("""
                Введите путь директории из которой должны быть прочитаны файлы в кеш 
                (текстовые файлы лежат например в 
                C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\gc\\leak\\files ): """);
        Scanner scanner = new Scanner(System.in);
        DirFileCache dirFileCache = new DirFileCache(scanner.nextLine());
        PrintFiles printFiles = new PrintFiles();


        while (choice == 1 || choice == 2) {
            System.out.println("Вы находитесь в директории " + dirFileCache.getCachingDir() + ". ");
            System.out.println("Директория содержит следующие текстовые файлы: ");
            Files.walkFileTree(Path.of(dirFileCache.getCachingDir()), printFiles);
            System.out.println("""
                    Введите 1 для кеширования файла.
                    Введите 2, для чтения файла из кеша.
                    Введите любое другое число для выхода.
                    """);
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Введите название файла для кеширования:");
                Scanner scanFileName = new Scanner(System.in);
                fileName = scanFileName.nextLine();
                String textFromFile = loadTextFromFile(dirFileCache.getCachingDir() + "\\" + fileName);

                if (dirFileCache.cache.get(fileName) == null) {
                    dirFileCache.put(fileName, textFromFile);
                } else {
                    System.out.println("File already in the cache. Read it.");
                }

            } else if (choice == 2) {
                System.out.println("Введите название файла, который желаете прочитать из кеша:");
                Scanner scanFileName = new Scanner(System.in);
                fileName = scanFileName.nextLine();

                if (dirFileCache.cache.get(fileName) == null) {
                    System.out.println("Cache of the File isn't load. Please load the cache first.");
                } else {
                    System.out.println("Содержимое файла " + fileName + " следующее:");
                    String s = dirFileCache.get(fileName);
                    System.out.println(s);
                }
            }
        }
    }

    public static String loadTextFromFile(String file) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                stringJoiner.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringJoiner.toString();
    }
}
