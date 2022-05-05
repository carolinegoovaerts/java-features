package ch.sourcequality.poc.java17.jep409;

import java.util.List;

final class CompositePattern {

    sealed interface Component permits Leaf, Composite {
        String display();
    }

    record Leaf(String name) implements Component {
        @Override
        public String display() {
            return name;
        }
    }

    record Composite(List<Component> components) implements Component {
        @Override
        public String display() {
            var csv = this.components.stream()
                    .map(Component::display)
                    .reduce("", (a, b) -> a.isEmpty() ? b : a + "," + b);
            return "[" + csv + "]";
        }
    }
}
