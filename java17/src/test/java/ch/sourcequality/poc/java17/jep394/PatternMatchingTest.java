package ch.sourcequality.poc.java17.jep394;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatternMatchingTest {

    private PatternMatching patternMatching;

    @BeforeEach
    void setUp() {
        patternMatching = new PatternMatching();
    }

    @Test
    void returnFieldValueWhenNotMatched() {
        String actual = patternMatching.determineText(new Object());
        assertEquals("initial", actual);
    }

    @Test
    void returnShadowedFieldValueWhenMatched() {
        assertEquals("this", patternMatching.determineText("this"));
    }

    @Test
    void throwExceptionWhenNotAnInteger() {
        assertThrows(IllegalArgumentException.class, () -> patternMatching.multiply(new Object()));
    }

    @Test
    void patternVariableInScopeWhenSuccessfullyAssigned() {
        var result = patternMatching.multiply(2);
        assertEquals(4, result);
    }

    @Test
    void trueWhenIntegerAndOdd() {
        assertTrue(patternMatching.isOdd(1));
    }

    @Test
    void falseWhenOtherThanInteger() {
        assertFalse(patternMatching.isOdd(1d));
    }
}
