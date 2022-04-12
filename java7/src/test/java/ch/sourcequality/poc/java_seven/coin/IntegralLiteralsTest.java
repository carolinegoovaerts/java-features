package ch.sourcequality.poc.java_seven.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntegralLiteralsTest {

    @Test
    void createBinaryLiteral() {
        int binary = 0b101010;
        Assertions.assertEquals(42, binary);
    }

    @Test
    void useUnderscoreAsPunctuationMark() {
        Assertions.assertEquals(1000000000, 1_000_000_000L);
    }

    @Test
    void useUnderscoreInMacAddress() {
        Assertions.assertEquals(42, 0x00_00_00_00_00_2a);
    }

    @Test
    void useAnyNumberOfUnderscores() {
        Assertions.assertEquals(1230, 1_2__3___0);
    }
}
