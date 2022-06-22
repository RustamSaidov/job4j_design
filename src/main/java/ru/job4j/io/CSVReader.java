package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        List<String> dataList = convertDataIntoListOfLines(argsName);
        List<String> filteredColumnsNamesList = new ArrayList<>();
        int lineCount = dataList.size();
        int columnsCount = getColumnsCount(dataList, ";");
        String[][] dataArray = new String[lineCount][columnsCount];
        convertingListOfLinesIntoArray(dataList, lineCount, columnsCount, dataArray, ";");
        filteredColumnsNamesList.add(argsName.getValues().get("filter"));
        int filteredColumnCount = getColumnsCount(filteredColumnsNamesList, ",");
        String[][] filteredColumnsNamesArray = new String[1][filteredColumnCount];
        convertingListOfLinesIntoArray(filteredColumnsNamesList, 1, filteredColumnCount, filteredColumnsNamesArray, ",");
        Set<String> filteredColumnsNamesSet = fillingSetByFilteredColumnsNames(filteredColumnsNamesArray);
        String[][] filteredDataArray = filterDataArray(filteredColumnsNamesSet, dataArray);
        saveToFilteredList(argsName, filteredDataArray);
    }

    private static String[][] filterDataArray(Set<String> filteredColumnsNamesSet, String[][] dataArray) {
        String[][] filteredDataArray = new String[dataArray.length][filteredColumnsNamesSet.size()];
        int k = 0;
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[0].length; j++) {
                if (filteredColumnsNamesSet.contains(dataArray[0][j])) {
                    filteredDataArray[i][k] = dataArray[i][j];
                    k++;
                }
            }
            k = 0;
        }
        return filteredDataArray;
    }

    private static Set<String> fillingSetByFilteredColumnsNames(String[][] filteredColumnsNamesArray) {
        Set<String> filteredColumnsNamesSet = new HashSet<>();
        for (int i = 0; i < filteredColumnsNamesArray.length; i++) {
            for (int j = 0; j < filteredColumnsNamesArray[0].length; j++) {
                filteredColumnsNamesSet.add(filteredColumnsNamesArray[i][j]);
            }
        }
        return filteredColumnsNamesSet;
    }

    private static List<String> convertDataIntoListOfLines(ArgsName argsName) {
        List<String> dataList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(argsName.getValues().get("path")))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                dataList.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private static int getColumnsCount(List<String> dataList, String delimiter) {
        int columnsCount = 0;
        var scanner = new Scanner(new ByteArrayInputStream(dataList.get(0).getBytes()))
                .useDelimiter(delimiter);
        while (scanner.hasNext()) {
            scanner.next();
            columnsCount++;
        }
        return columnsCount;
    }

    private static void convertingListOfLinesIntoArray(List<String> list, int lineCount, int columnsCount, String[][] dataArray, String delimiter) {
        for (int i = 0; i < lineCount; i++) {
            var scanner1 = new Scanner(new ByteArrayInputStream(list.get(i).getBytes()))
                    .useDelimiter(delimiter);
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
                    if (j < filteredDataArray[0].length - 1) {
                        out.print(filteredDataArray[i][j] + ";");
                    } else {
                        out.print(filteredDataArray[i][j]);
                    }
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
        ArgsName argsName = ArgsName.of(args);
        Files.writeString(file.toPath(), data);
        CSVReader.handle(argsName);
    }
}
