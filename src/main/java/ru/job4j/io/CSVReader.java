package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> dataList = new ArrayList<>();
        List<String> filteredColumnsNamesList = new ArrayList<>();
        Set<String> filteredColumnsNamesSet = new HashSet<>();

        int lineCount = 0;
        int columnsCount = 0;
        int filteredColumnCount = 0;
        convertDataIntoListOfLines(argsName, dataList);
        lineCount = dataList.size();
        columnsCount = getColumnsCount(dataList, columnsCount, ";");
        String[][] dataArray = new String[lineCount][columnsCount];

        convertingListOfLinesIntoArray(dataList, lineCount, columnsCount, dataArray, ";");

        filteredColumnsNamesList.add(argsName.getValues().get("filter"));
        filteredColumnCount = getColumnsCount(filteredColumnsNamesList, filteredColumnCount, ",");
        String[][] filteredColumnsNamesArray = new String[1][filteredColumnCount];
        convertingListOfLinesIntoArray(filteredColumnsNamesList, 1, filteredColumnCount, filteredColumnsNamesArray, ",");


        fillingSetByFilteredColumnsNames(filteredColumnsNamesSet, filteredColumnsNamesArray);
        System.out.println("set: " + filteredColumnsNamesSet);

        String[][] filteredDataArray = new String[lineCount][filteredColumnsNamesSet.size()];

        filterDataArray(filteredColumnsNamesSet, dataArray, filteredDataArray);


        System.out.println(Arrays.deepToString(filteredDataArray));
        System.out.println("----------------");
        System.out.println(Arrays.deepToString(filteredColumnsNamesArray));
        System.out.println("----------------");
        System.out.println(Arrays.deepToString(dataArray));
        System.out.println("----------------");

        saveToFilteredList(argsName, filteredDataArray);

    }

    private static void filterDataArray(Set<String> filteredColumnsNamesSet, String[][] dataArray, String[][] filteredDataArray) {
        int k = 0;
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[0].length; j++) {
                if (filteredColumnsNamesSet.contains(dataArray[0][j])) {
                    filteredDataArray[i][k] = dataArray[i][j];
                    System.out.println(filteredDataArray[i][k] + "!!!");
                    k++;
                }
            }
            k = 0;
        }
    }

    private static void fillingSetByFilteredColumnsNames(Set<String> filteredColumnsNamesSet, String[][] filteredColumnsNamesArray) {
        for (int i = 0; i < filteredColumnsNamesArray.length; i++) {
            for (int j = 0; j < filteredColumnsNamesArray[0].length; j++) {
                filteredColumnsNamesSet.add(filteredColumnsNamesArray[i][j]);
            }
        }
    }

    private static void convertDataIntoListOfLines(ArgsName argsName, List<String> dataList) {
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.getValues().get("path")))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                dataList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getColumnsCount(List<String> dataList, int columnsCount, String delimiter) {
        var scanner = new Scanner(new ByteArrayInputStream(dataList.get(0).getBytes()))
                .useDelimiter(delimiter
                        //";"
                );
        while (scanner.hasNext()) {
            scanner.next();
            columnsCount++;
        }
        return columnsCount;
    }

    private static void convertingListOfLinesIntoArray(List<String> list, int lineCount, int columnsCount, String[][] dataArray, String delimiter) {
        for (int i = 0; i < lineCount; i++) {
            var scanner1 = new Scanner(new ByteArrayInputStream(list.get(i).getBytes()))
                    .useDelimiter(delimiter
                            //";"
                    );
            for (int j = 0; j < columnsCount; j++) {
                String str = scanner1.next();
                dataArray[i][j] = str;
            }
        }
    }

    private static void saveToFilteredList(ArgsName argsName, String[][] filteredDataArray) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.getValues().get("out"))))) {
            for (int i = 0; i < filteredDataArray.length; i++) {
                for (int j = 0; j < filteredDataArray[0].length; j++) {
                    out.print(filteredDataArray[i][j] + " ");
                }
                out.println();
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
