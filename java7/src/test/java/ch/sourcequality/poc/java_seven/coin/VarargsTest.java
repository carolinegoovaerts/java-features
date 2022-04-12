package ch.sourcequality.poc.java_seven.coin;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class VarargsTest {

    @Test
    void x() {
        App.main(new String[]{"a", "b", "c"});
        App7.main("a", "b", "c");
    }

    static class App {
        public static void main(String[] args) {
            System.out.println(Arrays.asList(args));
        }
    }

    static class App7 {
        public static void main(String... args) {
            System.out.println(Arrays.asList(args));
        }
    }
}
