package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionalInterfacesTest {

    @Test
    void shouldMultiply() {
        Calculation multiplication = new Calculation() { // anonymous class
            @Override
            public int evaluate(int first, int second) {
                return first * second;
            }
        };
        assertEquals(9, multiplication.evaluate(3, 3));
    }

    @Test
    void shouldSubtract() {
        Calculation subtraction = (int a, int b) -> { // lambda expression: return value requires code block
            return a - b;
        };
        assertEquals(1, subtraction.evaluate(3, 2));
    }

    @Test
    void shouldDivide() {
        Calculation division = (a, b) -> a / b; // lambda expression: return value is evaluated at runtime
        assertEquals(3, division.evaluate(9, 3));
    }

    @Test
    void shouldAdd() {
        Calculation addition = (a, b) -> {
            return Integer.sum(a, b);
        };
        assertEquals(3, addition.evaluate(1, 2));
    }

    @FunctionalInterface // verifies FunctionalInterface criteria @compile time
    interface Calculation {
        int evaluate(int first, int second);
    }
}
