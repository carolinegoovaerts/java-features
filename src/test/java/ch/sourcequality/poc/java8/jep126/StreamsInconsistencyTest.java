package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import java.util.function.Function;

import static ch.sourcequality.poc.java8.jep126.Fixtures.LIST_OF_INTEGERS;
import static ch.sourcequality.poc.java8.jep126.Fixtures.emptyListOfIntegersListSize;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "SimplifyStreamApiCallChains", "UseBulkOperation"})
public class StreamsInconsistencyTest {

    private static Function<Integer, Integer> addToListUsingStatefulFunction(List<Integer> list) {
        return e -> {
            list.add(e);
            return e;
        };
    }

    @Test
    void throwExceptionWhenSourceIsModifiedWhileProcessingTheStream() {
        List<String> listOfStrings = new ArrayList<>(Arrays.asList("one", "two"));
        Executable act = () -> listOfStrings.stream()
                .peek(s -> listOfStrings.add("three"))
                .reduce((a, b) -> a + " " + b)
                .get();

        assertThrows(ConcurrentModificationException.class, act);
    }

    @Test
    void concatenateUsingAssociativeFunction() {
        List<String> listOfStrings = new ArrayList<>(Arrays.asList("one", "two"));
        String concatenation = listOfStrings.stream()
                .peek(System.out::println)
                .reduce((a, b) -> a + " " + b)
                .get();

        assertEquals("one two", concatenation);
    }

    @Test
    void demoSerialStorageUsingStatefulLambdaExpression() {
        List<Integer> threadUnsafeList = emptyListOfIntegersListSize();
        List<Integer> result = emptyListOfIntegersListSize();

        LIST_OF_INTEGERS.stream()
                .map(addToListUsingStatefulFunction(threadUnsafeList))
                .forEachOrdered(result::add);
        assertEquals(LIST_OF_INTEGERS, result);

        result.clear();
        threadUnsafeList.stream()
                .forEachOrdered(result::add);

        assertEquals(LIST_OF_INTEGERS, result);
    }

    @Test
    void demoParallelStorageUsingStatefulLambdaExpression() {
        List<Integer> threadSafeList = Collections.synchronizedList(emptyListOfIntegersListSize());
        List<Integer> result = emptyListOfIntegersListSize();

        LIST_OF_INTEGERS
                .parallelStream()
                .map(addToListUsingStatefulFunction(threadSafeList))
                .forEachOrdered(result::add);
        assertEquals(LIST_OF_INTEGERS, result);

        result.clear();
        threadSafeList
                .stream()
                .forEachOrdered(result::add);

        assertNotEquals(LIST_OF_INTEGERS, result);
    }
}
