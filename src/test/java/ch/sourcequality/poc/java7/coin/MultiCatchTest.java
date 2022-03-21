package ch.sourcequality.poc.java7.coin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiCatchTest {

    private static Result process(Integer number) {
        try {
            int result = 100 / number;
            return Result.OK;
        } catch (ArithmeticException | NullPointerException e) {
            return Result.NOK;
        }
    }

    @Test
    void shouldHandleExceptionsEqually() {
        assertEquals(Result.NOK, process(0));
        assertEquals(Result.NOK, process(null));
        assertEquals(Result.OK, process(1));
    }

    private enum Result {OK, NOK}
}
