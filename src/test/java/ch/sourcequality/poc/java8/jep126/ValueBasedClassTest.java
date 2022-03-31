package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class ValueBasedClassTest {

    @Test
    void demoOptionalAsValueBasedClass() {
        Optional<Integer> mightBeOne = Optional.of(1);
        Optional<Integer> mightBeOneToo = Optional.of(1);

        assertEquals(mightBeOne, mightBeOneToo);
        assertNotSame(mightBeOne, mightBeOneToo);
    }
}
