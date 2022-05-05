package ch.sourcequality.poc.java_eleven.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnmodifiableCollectionEnhancementsTest {

    @Test
    void demoUnmodifiableViewCollection() {
        var source = Arrays.asList(1, 2, 3);
        var view = Collections.unmodifiableList(source);

        source.set(2, -1);

        assertEquals(-1, view.get(2));
    }

    @Test
    void demoUnmodifiableListOf() {
        var collection = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, () -> collection.add(4));
    }

    @Test
    void demoUnmodifiedListCopyOf() {
        var source = new ArrayList<>(List.of(1, 2, 3));
        var copy = List.copyOf(source);

        source.add(4);

        assertEquals(3, copy.size());
    }

    @Test
    void demoUnmodifiedCollectionFromStream() {
        var collection = Stream.of(1, 2, 3).collect(Collectors.toUnmodifiableList());
        assertThrows(UnsupportedOperationException.class, () -> collection.set(1, 0));
    }
}
