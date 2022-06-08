package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> filterList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.contains(" 404 ")) {
                    filterList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filterList;
    }

    public static void lineBylineOutput(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        lineBylineOutput(log);
    }
}