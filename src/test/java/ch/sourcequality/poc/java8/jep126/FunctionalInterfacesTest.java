package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionalInterfacesTest {

    @FunctionalInterface // verifies FunctionalInterface criteria @compile time
    interface Calculation {
        int evaluate(int first, int second);
    }

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
    void shouldDivide() {
        Calculation division = (a, b) -> a / b; // lambda expression
        assertEquals(3, division.evaluate(9, 3));
    }

    @Test
    void shouldAdd() {
        Calculation addition = Integer::sum; // method reference
        assertEquals(3, addition.evaluate(1, 2));
    }
}
