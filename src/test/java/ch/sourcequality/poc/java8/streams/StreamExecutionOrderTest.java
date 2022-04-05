package ch.sourcequality.poc.java8.streams;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ch.sourcequality.poc.java8.streams.Fixtures.LIST_OF_INTEGERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SuppressWarnings({"SimplifyStreamApiCallChains", "UseBulkOperation"})
class StreamExecutionOrderTest {

    private static List<Integer> emptyList() {
        return new ArrayList<>(LIST_OF_INTEGERS.size());
    }

    @Test
    void shouldStoreInInitialOrder() {
        List<Integer> result = emptyList();
        LIST_OF_INTEGERS
                .stream()
                .forEach(result::add);

        assertEquals(LIST_OF_INTEGERS, result);
    }

    @Test
    void shouldStoreInReverseOrder() {
        Comparator<Integer> normalCompare = Integer::compare;
        List<Integer> result = emptyList();

        LIST_OF_INTEGERS.sort(normalCompare.reversed());
        LIST_OF_INTEGERS
                .stream()
                .forEach(result::add);

        assertEquals(List.of(8, 7, 6, 5, 4, 3, 2, 1), result);
    }

    @Test
    void shouldStoreInParallel() {
        List<Integer> result = emptyList();
        LIST_OF_INTEGERS
                .parallelStream()
                .forEach(result::add);

        assertNotEquals(LIST_OF_INTEGERS, result);
    }

    @Test
    void shouldStoreInParallelUsingForEachOrdered() {
        List<Integer> result = emptyList();
        LIST_OF_INTEGERS
                .parallelStream()
                .forEachOrdered(result::add);

        assertEquals(LIST_OF_INTEGERS, result);
    }
}
