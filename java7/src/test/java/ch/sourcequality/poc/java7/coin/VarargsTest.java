package ch.sourcequality.poc.java7.coin;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VarargsTest {

    @Test
    void varargsEqualsArray() {
        List<String> array = App.toList(new String[]{"a", "b", "c"});
        List<String> varargs = App7.toList("a", "b", "c");

        assertEquals(array, varargs);
    }

    static class App {
        public static List<String> toList(String[] args) {
            return Arrays.asList(args);
        }
    }

    static class App7 {
        public static List<String> toList(String... args) {
            return Arrays.asList(args);
        }
    }
}
