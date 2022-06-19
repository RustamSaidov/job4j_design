package ru.job4j.io;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        //List<String> filterList = new ArrayList<>();
        List<String> dataList = new ArrayList<>();

        int lineCount = 0;
        int columnsCount = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.getValues().get("path")))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                dataList.add(line);
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        var scanner = new Scanner(new ByteArrayInputStream(dataList.get(0).getBytes()))
                .useDelimiter(";");
        while (scanner.hasNext()) {
            scanner.next();
            columnsCount++;
        }
        String[][] dataArray = new String[lineCount][columnsCount];
        String[][] filterArray = new String[lineCount][columnsCount];


        for (int i = 0; i < lineCount; i++) {
            var scanner1 = new Scanner(new ByteArrayInputStream(dataList.get(i).getBytes()))
                    .useDelimiter(";");
            for (int j = 0; j < columnsCount; j++) {
                String str = scanner1.next();
                System.out.println(str + "&&&");
                dataArray[i][j] = str;
            }
        }

        System.out.println(Arrays.deepToString(dataArray));
        System.out.println("----------------");

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("out")))) {
            for (int i = 0; i < filterArray.length; i++) {
                for(int j = 0; j < filterArray[0].length; j++){
                    out.println(filterArray[i][j]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = new File("source.csv");
        File target = new File("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);


    }
}
