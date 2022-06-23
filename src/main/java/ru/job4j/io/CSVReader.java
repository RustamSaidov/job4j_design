package ru.job4j.io;

import java.io.*;
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
        outFilteredResult(argsName, filteredDataArray);
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

    private static void outFilteredResult(ArgsName argsName, String[][] filteredDataArray) {
        if ("stdout".equals(argsName.getValues().get("out"))) {
            outResultToConsole(filteredDataArray);

        } else {
            saveToFilteredList(argsName, filteredDataArray);
        }
    }

    private static void outResultToConsole(String[][] filteredDataArray) {
        for (int i = 0; i < filteredDataArray.length; i++) {
            for (int j = 0; j < filteredDataArray[0].length; j++) {
                if (j < filteredDataArray[0].length - 1) {
                    System.out.print(filteredDataArray[i][j] + ";");
                } else {
                    System.out.print(filteredDataArray[i][j]);
                }
            }
            System.out.println();
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

    private static void argumentTransferValidation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
    }

    private static void folderAdressValidatoin(ArgsName argsName) {
        File fullPath = new File(argsName.getValues().get("path"));
        File file = new File(fullPath.getParent());

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Folder %s Not exist ", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Folder %s Not directory ", file.getAbsoluteFile()));
        }
    }

    private static void fileExtentionValidation(ArgsName argsName) {
        File file = new File(argsName.getValues().get("path"));

        if (!file.getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Wrong file extension for search. Change the file extension search "
                    + "argument according to the search conditions.");
        }
    }

    private static void delimiterTypeValidation(ArgsName argsName) {
        String delimiter = argsName.getValues().get("delimiter");

        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Wrong delimiter sign. Change the delimiter sign "
                    + "argument according to the file extention.");
        }
    }

    static void csvArgumentsValidation(ArgsName argsName) {
        folderAdressValidatoin(argsName);
        fileExtentionValidation(argsName);
        delimiterTypeValidation(argsName);
    }

    public static void main(String[] args) throws Exception {
        argumentTransferValidation(args);
        ArgsName argsName = ArgsName.of(args);
        csvArgumentsValidation(argsName);
        CSVReader.handle(argsName);
    }
}