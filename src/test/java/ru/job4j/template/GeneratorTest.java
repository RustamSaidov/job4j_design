/*package ru.job4j.template;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
public class GeneratorTest {

    @Test
    public void whenGenerateWell() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        Generator generator = new PhraseGenerator();
        String phrase = generator.produce("I am a ${name}, Who are ${subject}? ", map);
        assertThat(phrase).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenWrongTemplate() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        Generator generator = new PhraseGenerator();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}, ${speciality}? ", map);
        });
    }

    @Test
    public void whenEmptyTemplate() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        Generator generator = new PhraseGenerator();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("", map);
        });
    }

    @Test
    public void whenWrongMapKey() {
        Map<String, String> map = new HashMap<>();
        map.put("na", "Petr Arsentev");
        map.put("subject", "you");
        Generator generator = new PhraseGenerator();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}, ${speciality}? ", map);
        });
    }

    @Test
    public void whenEmptyMap() {
        Map<String, String> map = new HashMap<>();
        Generator generator = new PhraseGenerator();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}, ${speciality}? ", map);
        });
    }
}

 */

