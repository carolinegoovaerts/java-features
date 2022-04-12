package ch.sourcequality.poc.java_eight.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Fixtures {
    static final Integer[] INTEGERS = {1, 2, 3, 4, 5, 6, 7, 8};
    static final List<Integer> LIST_OF_INTEGERS = new ArrayList<>(Arrays.asList(INTEGERS));

    static ArrayList<Integer> emptyListOfIntegersListSize() {
        return new ArrayList<>(INTEGERS.length);
    }
}
