package ch.sourcequality.poc.java17.jep378;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import static ch.sourcequality.poc.java17.jep378.TextBlocks.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextBlocksTest {

    @Test
    void demoSql() {
        var expected = String.format(ONE_DIMENSIONAL_SQL, "text");
        var result = String.format(TWO_DIMENSIONAL_SQL, "text");

        assertEquals(expected, result);
    }

    @Test
    void demoYaml() {
        var yaml = new Yaml();
        var expected = Map.of("numbers", Map.of("one", 1, "two", 2, "three", 3));

        var load = yaml.load(TWO_DIMENSIONAL_YAML);

        assertEquals(expected, load);
    }
}
