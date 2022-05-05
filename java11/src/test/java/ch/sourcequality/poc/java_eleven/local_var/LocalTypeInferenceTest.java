package ch.sourcequality.poc.java_eleven.local_var;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalTypeInferenceTest {

    @Test
    void shouldInferToMapOfObjectToObject() {
        // diamond syntax since JDK 7
        HashMap<Object, Object> expected = new HashMap<>();
        // local type inference since JDK 10
        var localTypeInferredMap = new HashMap<>();

        assertEquals(expected, localTypeInferredMap);
    }

    @Test
    void shouldInferToMapOfIntegerToString() {
        var localTypeInferredMap = new HashMap<>();
        localTypeInferredMap.put(1, "");

        assertEquals("", localTypeInferredMap.get(1));
    }

    @Test
    void demoTypeInferenceInBasicFor() {
        var count = 0;
        for (var i = 0; i < 5; i++) {
            count++;
        }
        assertEquals(5, count);
    }

    @Test
    void demoTypeInferenceInEnhancedFor() {
        for (var i : Fruit.values()) {
            assertTrue(i instanceof Fruit);
        }
    }

    @Test
    void demoLocalTypeInferenceInTryWithResources() throws IOException {
        var path = this.getClass().getResource("/lorem-ipsum.txt").getPath();

        try (var reader = new BufferedReader(new FileReader(path))) {
            assertEquals("Lorem ipsum", reader.readLine());
        }
    }

    private enum Fruit {APPLE}
}
