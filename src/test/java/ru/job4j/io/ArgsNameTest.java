package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request"), is("?msg=Exit="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[]{"-enconding=UTF-8", "-Xmx="});
        jvm.get("Xmx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyArray() {
        String[] array = new String[0];
        ArgsName jvm = ArgsName.of(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsAbsent() {
        ArgsName jvm = ArgsName.of(new String[] {"-=UTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSignEqualsIsAbsent() {
        ArgsName jvm = ArgsName.of(new String[] {"-encodingUTF-8"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSignDashIsAbsent() {
        ArgsName jvm = ArgsName.of(new String[] {"encoding=UTF-8"});
    }
}