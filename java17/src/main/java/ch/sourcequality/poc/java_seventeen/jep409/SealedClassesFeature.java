package ch.sourcequality.poc.java_seventeen.jep409;

import java.util.List;

class SealedClassesFeature {

    private static final List<RedBlueGreen> COLORS = List.of(new Red(), new LightBlue(), new Green());

    RedBlueGreen randomColor() {
        var index = (int) (Math.random() * COLORS.size());
        return COLORS.get(index);
    }

    RedBlueGreen red() {
        return new Red();
    }
}
