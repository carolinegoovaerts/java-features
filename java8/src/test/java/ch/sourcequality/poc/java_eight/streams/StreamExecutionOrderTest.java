package ch.sourcequality.poc.java_eight.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"SimplifyStreamApiCallChains", "UseBulkOperation"})
class StreamExecutionOrderTest {

    private static List<Integer> emptyList() {
        return new ArrayList<>(Fixtures.LIST_OF_INTEGERS.size());
    }

    @Test
    void shouldStoreInInitialOrder() {
        List<Integer> result = emptyList();
        Fixtures.LIST_OF_INTEGERS
                .stream()
                .forEach(result::add);

        Assertions.assertEquals(Fixtures.LIST_OF_INTEGERS, result);
    }

    @Test
    void shouldStoreInReverseOrder() {
        Comparator<Integer> normalCompare = Integer::compare;
        List<Integer> result = emptyList();

        Fixtures.LIST_OF_INTEGERS.sort(normalCompare.reversed());
        Fixtures.LIST_OF_INTEGERS
                .stream()
                .forEach(result::add);

        assertEquals(Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1), result);
    }

    @Test
    void shouldStoreInParallel() {
        List<Integer> result = emptyList();
        Fixtures.LIST_OF_INTEGERS
                .parallelStream()
                .forEach(result::add);

        Assertions.assertNotEquals(Fixtures.LIST_OF_INTEGERS, result);
    }

    @Test
    void shouldStoreInParallelUsingForEachOrdered() {
        List<Integer> result = emptyList();
        Fixtures.LIST_OF_INTEGERS
                .parallelStream()
                .forEachOrdered(result::add);

        Assertions.assertEquals(Fixtures.LIST_OF_INTEGERS, result);
    }
}
