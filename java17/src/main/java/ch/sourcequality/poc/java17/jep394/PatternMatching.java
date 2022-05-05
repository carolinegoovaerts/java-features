package ch.sourcequality.poc.java17.jep394;

final class PatternMatching {
    private final String text = "initial";

    String determineText(Object o) {
        if (o instanceof String text) {
            return text;
        }
        return text;
    }

    Integer multiply(Object o) {
        if (!(o instanceof Integer i)) {
            throw new IllegalArgumentException();
        }
        return i * i;
    }

    boolean isOdd(Object o) {
        return o instanceof Integer i && i % 2 == 1;
    }
}
