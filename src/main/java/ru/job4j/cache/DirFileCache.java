package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        try (BufferedReader in = new BufferedReader(new FileReader(Path.of(cachingDir, key).toFile()))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                stringJoiner.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringJoiner.toString();
    }

    public String getCachingDir() {
        return cachingDir;
    }

    @Override
    public void put(String key, String value) {
        SoftReference<String> soft = new SoftReference<>(value);
        cache.put(key, soft);
    }

    @Override
    public String get(String key) {
        SoftReference<String> soft = cache.getOrDefault(key, null);
        String str;
        if (soft == null) {
            String textFromFile = load(key);
            put(key, textFromFile);
            str = cache.get(key).get();
        } else {
            str = soft.get();
        }
        return str;
    }
}