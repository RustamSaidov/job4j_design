package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
        assertThat(array).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).doesNotEndWith("x");
                });
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "first");
        assertThat(list).isNotEmpty()
                .hasSize(6)
                .doesNotContain("seven")
                .startsWith("first")
                .endsWith("first")
                .containsSequence("three", "four");
        assertThat(list).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).doesNotContain("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
                });

    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four");
        assertThat(set).isNotEmpty()
                .hasSize(4)
                .contains("three", "four")
                .containsExactlyInAnyOrder("first", "three", "four", "second")
                .containsAnyOf("three")
                .doesNotContain("five");
        assertThat(set).isNotNull()
                .allMatch(e -> e.length() > 0)
                .anyMatch(e -> e == "three");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = Map.of("1", 111, "2", 222, "3", 333, "4", 444);
        assertThat(map).hasSize(4)
                .containsKeys("1", "3")
                .containsValues(444, 222)
                .doesNotContainKey("5")
                .doesNotContainValue(555)
                .containsEntry("3", 333);
    }
}