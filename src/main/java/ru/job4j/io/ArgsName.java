package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) throws IllegalArgumentException {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Parameter with key " + key + " does not exist");
        }
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        argumentValidation(args);
        for (int i = 0; i < args.length; i++) {
            String substr1 = args[i].substring(1, args[i].indexOf('='));
            String substr2 = args[i].substring(args[i].indexOf('=') + 1);
            values.put(substr1, substr2);
        }
    }

    private static void argumentValidation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The passed array of parameters is empty");
        }
        for (int i = 0; i < args.length; i++) {

            if (!args[i].startsWith("-")) {
                throw new IllegalArgumentException("The '-' sign of the beginning of the \"-key=value\" type argument was not found");
            }
            if (args[i].indexOf('=') == args.length) {
                throw new IllegalArgumentException("The value of the \"value\" field is missing");
            }
            if (!args[i].contains("=")) {
                throw new IllegalArgumentException("The '=' sign between \"-key=value\" type argument was not found");
            }
            if (args[i].indexOf('=') == 1) {
                throw new IllegalArgumentException("The \"key\" type argument was not found");
            }

            if (args[i].indexOf('=') == args[i].length() - 1) {
                throw new IllegalArgumentException("The \"value\" type argument was not found");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}