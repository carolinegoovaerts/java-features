package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"Convert2MethodRef", "Convert2Lambda", "CodeBlock2Expr"})
class FunctionalInterfacesTest {

    @Test
    void anonymousClass() {
        Calculation<Integer> multiplication = new Calculation<Integer>() {
            @Override
            public Integer evaluate(Integer first, Integer second) {
                return first * second;
            }
        };
        assertEquals(9, multiplication.evaluate(3, 3));
    }

    @Test
    void lambdaExpressionWithCodeBlock() {
        Calculation<Integer> subtraction = (Integer a, Integer b) -> {
            return a - b;
        };
        assertEquals(1, subtraction.evaluate(3, 2));
    }

    @Test
    void implicitlyTypedLambdaExpression() {
        Calculation<Integer> addition = (a, b) -> {
            return Integer.sum(a, b);
        };
        assertEquals(3, addition.evaluate(1, 2));
    }

    @Test
    void lambdaExpressionHavingReturnValueEvaluatedAtRuntime() {
        Calculation<Double> division = (a, b) -> a / b;
        assertEquals(3, division.evaluate(9d, 3d));
    }

    @Test
    void omitTheNameOfTheFunctionalInterfaceMethod() {
        double[] result = DoubleStream.of(1, 2, 4)
                .map(new Reciprocal())
                .toArray();

        assertArrayEquals(new double[]{1, 0.5, 0.25}, result);
    }

    @FunctionalInterface
    private interface Calculation<T> {
        T evaluate(T first, T second);
    }

    private static final class Reciprocal implements DoubleUnaryOperator {
        @Override
        public double applyAsDouble(double operand) {
            return 1 / operand;
        }
    }
}
