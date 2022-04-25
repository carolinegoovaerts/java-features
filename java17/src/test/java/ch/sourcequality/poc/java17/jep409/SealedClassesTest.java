package ch.sourcequality.poc.java17.jep409;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SealedClassesTest {

    private SealedClassesFeature sealedClassesFeature;

    @BeforeEach
    void setUp() {
        sealedClassesFeature = new SealedClassesFeature();
    }

    @Test
    void shouldReturnRed() {
        var red = sealedClassesFeature.red();
        assertEquals("Red", red.toString());
    }

    @Test
    void shouldReturnDifferentColors() {
        Set<RedBlueGreen> colors = new HashSet<>();
        IntStream.range(1, 5).forEach(i -> colors.add(sealedClassesFeature.randomColor()));

        assertTrue(colors.size() > 1);
    }

    @Test
    void shouldBeEqualWhenNameIsTheSame() {
        assertEquals(new LightBlue(), new LightBlue());
    }
}
