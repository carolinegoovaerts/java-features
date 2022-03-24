package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MethodReferencesTest {

    private static void assertSortingInDescendingOrder(Comparator<? super Double> comparator) {
        Double[] elements = {1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Arrays.sort(elements, comparator);
        assertArrayEquals(expected, elements);
    }

    @Test
    void useReferenceToAStaticMethod() {
        Comparator<Double> comparator = (a, b) -> -Double.compare(a, b);
        assertSortingInDescendingOrder(comparator);
    }

    @Test
    void referenceInstanceMethodOfAParticularObject() {
        Comparator<Double> comparator = new InverseDoubleComparator()::compare;
        assertSortingInDescendingOrder(comparator);
    }

    @Test
    void omitTheNameOfTheFunctionalInterfaceMethod() {
        Comparator<Double> comparator = new InverseDoubleComparator();
        assertSortingInDescendingOrder(comparator);
    }

    @Test
    void x() {
        // TODO finish "Kinds of Method References"
        //  in https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.htmlKinds
        fail("not yet implemented");
    }

    private static final class InverseDoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return -Double.compare(o1, o2);
        }
    }
}
