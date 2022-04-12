package ch.sourcequality.poc.java_seven.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(Result.NOK, process(0));
        Assertions.assertEquals(Result.NOK, process(null));
        Assertions.assertEquals(Result.OK, process(1));
    }

    private enum Result {OK, NOK}
}
