package ch.sourcequality.poc.java17.jep361;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SwitchExpressionsTest {

    private static Object exercise(Color color) {
        return switch (color) {
            case RED -> 1;
            case LIGHT_BLUE -> throw new IllegalArgumentException();
            case GREEN -> "a";
            case PURPLE -> Color.class;
        };
    }

    @Test
    void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> exercise(Color.LIGHT_BLUE));
    }

    @Test
    void shouldReturnInteger() {
        var i = exercise(Color.RED);
        assertTrue(i instanceof Integer);
    }

    private enum Color {RED, LIGHT_BLUE, GREEN, PURPLE}
}
