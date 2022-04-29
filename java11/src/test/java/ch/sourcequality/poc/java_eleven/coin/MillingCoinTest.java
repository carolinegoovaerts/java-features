package ch.sourcequality.poc.java_eleven.coin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MillingCoinTest {

    @Test
    void shouldCompileWithoutWarnings() {
        CollectionsFixture fixture = new CollectionsFixture();
        List<Integer> collection = new ArrayList<>();
        fixture.addAll(collection, 1, 2);

        assertEquals(List.of(1, 2), collection);
    }

    void demoTryWithResourcesWithFinalVariable() {
        fail("not yet implemented");
    }
}
