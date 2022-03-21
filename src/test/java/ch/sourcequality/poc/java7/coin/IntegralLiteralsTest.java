package ch.sourcequality.poc.java7.coin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegralLiteralsTest {

    @Test
    void createBinaryLiteral() {
        int binary = 0b101010;
        assertEquals(42, binary);
    }

    @Test
    void useUnderscoreAsPunctuationMark() {
        assertEquals(1000000000, 1_000_000_000L);
    }

    @Test
    void useUnderscoreInMacAddress() {
        assertEquals(42, 0x00_00_00_00_00_2a);
    }

    @Test
    void useAnyNumberOfUnderscores() {
        assertEquals(1230, 1_2__3___0);
    }
}
