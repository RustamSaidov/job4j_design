package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) throws IOException {
        String rsl = "";

        rsl = Files.readString(Path.of(cachingDir, key));

        return rsl;
    }

    public String getCachingDir() {
        return cachingDir;
    }
}