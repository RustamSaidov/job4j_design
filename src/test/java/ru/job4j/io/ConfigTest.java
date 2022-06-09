package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        ru.job4j.io.Config config = new ru.job4j.io.Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenFileWithCommentsAndEmptyLines() {
        String path = "./data/file_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        Map<String, String> newMap = new HashMap<String, String>();
        newMap.put("key1", "value1");
        newMap.put("key2", "value2");
        newMap.put("key3", "value3");
        assertTrue(config.getValues().equals(newMap));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgumentExeption1() {
        String path = "./data/file_with_illegalargumentexeption1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgumentExeption2() {
        String path = "./data/file_with_illegalargumentexeption2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgumentExeption3() {
        String path = "./data/file_with_illegalargumentexeption3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenFileWithLinesWithTwoEqualSigns() {
        String path = "./data/file_with_lines_with_two_equal_signs.properties";
        Config config = new Config(path);
        config.load();
        Map<String, String> newMap = new HashMap<String, String>();
        newMap.put("key1", "value1=");
        newMap.put("key2", "value2=999");
        assertTrue(config.getValues().equals(newMap));
    }


}