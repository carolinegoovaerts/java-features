package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MethodReferencesTest {

    private static void assertSorting(Double[] expected, Double[] source, Comparator<Double> comparator) {
        // Java compiler infers the method type arguments to be (Double, Double)
        Arrays.sort(source, comparator);
        assertArrayEquals(expected, source);
    }

    // based on https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
    private static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferSource(SOURCE source, Supplier<DEST> supplier) {
        DEST result = supplier.get();
        result.addAll(source);

        return result;
    }

    @Test
    void lambdaExpressionReferencingAStaticMethod() {
        Double[] source = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Comparator<Double> comparator = (Double a, Double b) -> -Double.compare(a, b);

        assertSorting(expected, source, comparator);
    }

    @Test
    void referenceToAStaticMethod() {
        Double[] source = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{0d, 1d, 99d};
        Comparator<Double> comparator = Double::compare;

        assertSorting(expected, source, comparator);
    }

    @SuppressWarnings("FunctionalExpressionCanBeFolded")
    @Test
    void referenceToAnInstanceMethodOfAParticularObject() {
        Double[] source = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{99d, 1d, 0d};
        Comparator<Double> comparator = new InverseDoubleComparator()::compare;

        assertSorting(expected, source, comparator);
    }

    @Test
    void referenceToAnInstanceMethodOfAnArbitraryObjectOfAParticularType() {
        Double[] source = new Double[]{1d, 0d, 99d};
        Double[] expected = new Double[]{0d, 1d, 99d};
        Comparator<Double> comparator = Double::compareTo;

        assertSorting(expected, source, comparator);
    }

    @Test
    void referenceToAConstructor() {
        List<Integer> source = Arrays.asList(1, 2, 3);
        Supplier<Set<Integer>> setSupplier = HashSet::new;
        Set<Integer> result = transferSource(source, setSupplier);

        assertEquals(new HashSet<>(Arrays.asList(1, 2, 3)), result);
    }

    private static final class InverseDoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double o1, Double o2) {
            return -Double.compare(o1, o2);
        }
    }
}
