package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MethodReferencesTest {

    private static void assertSorting(Double[] expected, Double[] elements, Comparator<Double> comparator) {
        Arrays.sort(elements, comparator);
        assertArrayEquals(expected, elements);
    }

    @Test
    void referenceToAStaticMethod() {
        Double[] elements = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{0d, 1d, 99d};
        Comparator<Double> comparator = Double::compare;

        assertSorting(expected, elements, comparator);
    }

    @Test
    void referenceToAnInstanceMethodOfAParticularObject() {
        Double[] elements = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Comparator<Double> comparator = new InverseDoubleComparator()::compare;

        assertSorting(expected, elements, comparator);
    }

    @Test
    void referenceToAnInstanceMethodOfAnArbitraryObjectOfAParticularType() {
        Double[] elements = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{0d, 1d, 99d};
        Comparator<Double> comparator = Double::compareTo;

        assertSorting(expected, elements, comparator);
    }

    @Test
    void referenceToAConstructor() {
        fail("not yet implemented");
    }

    // TODO move to FunctionalInterfacesTest?
    @Test
    void lambdaExpressionReferencingAStaticMethod() {
        Double[] elements = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Comparator<Double> comparator = (a, b) -> -Double.compare(a, b);

        assertSorting(expected, elements, comparator);
    }

    @Test
    void omitTheNameOfTheFunctionalInterfaceMethod() {
        Double[] elements = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Comparator<Double> comparator = new InverseDoubleComparator();

        assertSorting(expected, elements, comparator);
    }

    private static final class InverseDoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return -Double.compare(o1, o2);
        }
    }
}
