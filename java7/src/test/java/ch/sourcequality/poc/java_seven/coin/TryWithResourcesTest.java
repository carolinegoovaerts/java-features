package ch.sourcequality.poc.java_seven.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class TryWithResourcesTest {

    static String firstLineFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    static String firstLineFromFileUsingAutocloseable(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    @Test
    void shouldReadFirstLine() throws IOException {
        String path = this.getClass().getResource("/lines.txt").getPath();
        Assertions.assertEquals("hello", firstLineFromFile(path));
    }

    @Test
    void shouldReadFirstLineUsingAutocloseable() throws IOException {
        String path = this.getClass().getResource("/lines.txt").getPath();
        Assertions.assertEquals("hello", firstLineFromFileUsingAutocloseable(path));
    }
}
