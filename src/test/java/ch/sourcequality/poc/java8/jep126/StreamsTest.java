package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamsTest {

    private static final Fruit[] FRUITS = new Fruit[]{new Fruit(Color.RED, "apple", 58),
            new Fruit(Color.ORANGE, "orange", 62),
            new Fruit(Color.RED, "strawberry", 49)
    };

    @Test
    void countRedFruitsUsingAStream() {
        long redFruitCount = Arrays.stream(FRUITS)
                .filter(fruit -> fruit.color == Color.RED)
                .count();
        assertEquals(2, redFruitCount);
    }

    @Test
    void calculateAverageCaloriesInRedFruits() {
        double average = Arrays.stream(FRUITS)
                .filter(fruit -> fruit.color == Color.RED)
                .mapToInt(fruit -> fruit.calories)
                .average()
                .getAsDouble();
        assertEquals(53.5, average);
    }

    private enum Color {
        ORANGE, RED
    }

    private static final class Fruit {
        private final Color color;
        private final String name;
        private final int calories;

        private Fruit(Color color, String name, int calories) {
            this.color = color;
            this.name = name;
            this.calories = calories;
        }
    }
}
