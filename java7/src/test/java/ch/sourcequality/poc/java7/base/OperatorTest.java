package ch.sourcequality.poc.java7.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperatorTest {

    @Test
    void unaryOperatorInvertsBitPattern() {
        byte pattern = 0b00110011;
        byte expected = (byte) 0b11001100;

        byte result = (byte) ~pattern;

        assertEquals(expected, result);
    }

    @Test
    void signedLeftShiftOperatorShiftsPatternToTheLeft() {
        byte numberOfPositions = 1;
        byte pattern = (byte) 0b11001100;
        byte expected = (byte) 0b10011000;

        byte result = (byte) (pattern << numberOfPositions);

        assertEquals(expected, result);
    }

    @Test
    void signedRightShiftOperatorShiftsPatternToTheRight() {
        byte numberOfPositions = 1;
        byte pattern = (byte) 0b11001100;
        byte expected = (byte) 0b11100110;

        byte result = (byte) (pattern >> numberOfPositions);

        assertEquals(expected, result);
    }

    @Test
    void unsignedRightShiftOperatorShiftsZeroIntoTheLeftmostPosition() {
        assertEquals(1, 3 >>> 1);
        assertEquals(5, 10 >>> 1);
        assertEquals(50, 100 >>> 1);
        assertEquals(25, 100 >>> 2);
    }
}
