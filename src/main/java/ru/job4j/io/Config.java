package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!line.isEmpty()
                        && line.length() - 1 == line.lastIndexOf("=")
                        && line.indexOf("=") == line.lastIndexOf("=")
                        || !line.isEmpty() && 0 == line.indexOf("=")
                        || line.equals("=")
                ) {
                    throw new IllegalArgumentException();
                }
                if (line.contains("=") && !line.contains("#")) {
                    String key = line.substring(0, line.indexOf('='));
                    String val = line.substring(line.indexOf('=') + 1);
                    values.put(key, val);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        System.out.println(config);
        config.load();
        System.out.println(config.values);
        System.out.println(config.values.keySet());
        System.out.println(config.values.values());
    }
}