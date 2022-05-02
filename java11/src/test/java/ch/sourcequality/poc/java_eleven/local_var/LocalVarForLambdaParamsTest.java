package ch.sourcequality.poc.java_eleven.local_var;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalVarForLambdaParamsTest {

    @Test
    void demoLocalTypeInferenceWithLambdas() {
        // since JDK 11
        Calculation<Integer> subtraction = (var a, var b) -> a - b;
        var result = subtraction.evaluate(1, 2);

        assertEquals(-1, result);
    }

    private interface Calculation<T> {
        T evaluate(T first, T second);
    }
}
