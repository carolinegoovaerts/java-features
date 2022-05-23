package ch.sourcequality.poc.java7.coin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ConcurrencyTest {

    private static SquaresTask squaresTaskFor(long[] numbers) {
        return new SquaresTask(numbers, 0, numbers.length);
    }

    @Test
    void demoSimpleForkJoin() {
        long[] numbers = {1, 2, 3, 4, 5};
        SquaresTask task = squaresTaskFor(numbers);

        new ForkJoinPool().invoke(task);

        assertArrayEquals(new long[]{1, 4, 9, 16, 25}, numbers);
    }
}
