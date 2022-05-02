package ch.sourcequality.poc.java_eleven.coin;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MillingCoinTest {

    private static String firstLineFromFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        try (reader) {
            return reader.readLine();
        }
    }

    @Test
    void shouldCompileWithoutWarnings() {
        CollectionsFixture fixture = new CollectionsFixture();
        List<Integer> collection = new ArrayList<>();
        fixture.addAll(collection, 1, 2);

        assertEquals(List.of(1, 2), collection);
    }

    @Test
    void demoTryWithResourcesWithFinalVariable() throws Exception {
        String resource = "/lorem-ipsum.txt";
        String expected = "Lorem ipsum ...";

        String firstLine = firstLineFromFile(pathToResource(resource));

        assertEquals(expected, firstLine);
    }

    @Test
    void demoDiamondSyntaxInAnonymousClass() {
        int expected = 1;
        Repository<Integer> simpleRepo = new Repository<>() {
            @Override
            public Integer getFirst() {
                return expected;
            }
        };
        assertEquals(expected, simpleRepo.getFirst());
    }

    private String pathToResource(String resource) {
        return this.getClass().getResource(resource).getPath();
    }
}
