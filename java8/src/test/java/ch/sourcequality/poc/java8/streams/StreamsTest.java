package ch.sourcequality.poc.java8.streams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("OptionalGetWithoutIsPresent")
class StreamsTest {
    private static final String ANY_WHITESPACE = " +";
    private static final Fruit APPLE = new Fruit(Color.RED, "apple", 58);
    private static final Fruit ORANGE = new Fruit(Color.ORANGE, "orange", 62);
    private static final Fruit STRAWBERRY = new Fruit(Color.RED, "strawberry", 49);

    @Test
    void demoStreamWithReductionOperation() {
        long redFruitCount = Arrays.stream(new Fruit[]{APPLE, ORANGE, STRAWBERRY})
                .filter(fruit -> fruit.color == Color.RED)
                .count();
        assertEquals(2, redFruitCount);
    }

    @Test
    void calculateAverageCaloriesInRedFruits() {
        double average = Arrays.stream(new Fruit[]{APPLE, ORANGE, STRAWBERRY})
                .filter(fruit -> fruit.color == Color.RED)
                .mapToInt(fruit -> fruit.calories)
                .average()
                .getAsDouble();
        assertEquals(53.5, average);
    }

    @Test
    void demoMultiLevelReduction() {
        Function<Fruit, Color> classifier = f -> f.color;
        Function<Fruit, String> mapper = f -> f.name;
        Collector<String, ?, List<String>> collector = Collectors.toList();
        Collector<Fruit, ?, List<String>> downstreamCollector = Collectors.mapping(mapper, collector);

        Map<Color, List<String>> fruitsByColor = Arrays.stream(new Fruit[]{APPLE, ORANGE, STRAWBERRY})
                .collect(Collectors.groupingBy(classifier, downstreamCollector));

        assertEquals(Collections.singletonList("orange"), fruitsByColor.get(Color.ORANGE));
    }

    @Test
    void aggregateCaloriesByFruitColor() {
        Function<Fruit, Color> classifier = f -> f.color;
        int identity = 0;
        Function<Fruit, Integer> mapper = f -> f.calories;
        BinaryOperator<Integer> operator = Integer::sum;
        Collector<Fruit, ?, Integer> downstreamCollector = Collectors.reducing(identity, mapper, operator);

        Map<Color, Integer> caloriesByFruitColor = Arrays.stream(new Fruit[]{APPLE, ORANGE, STRAWBERRY})
                .collect(Collectors.groupingBy(classifier, downstreamCollector));

        assertEquals(107, caloriesByFruitColor.get(Color.RED));
    }

    @Test
    void calculateSumInParallel() {
        Integer sum = Arrays.asList(new Fruit[]{APPLE, ORANGE, ORANGE}).parallelStream()
                .mapToInt(f -> f.calories)
                .reduce(Integer::sum)
                .getAsInt();
        assertEquals(182, sum);
    }

    @Test
    void countFruitUsingConcurrentReduction() {
        Map<String, Long> fruitCount = Stream.of(APPLE, ORANGE, STRAWBERRY, APPLE, APPLE).parallel()
                .collect(Collectors.groupingByConcurrent(f -> f.name, Collectors.counting()));

        assertTrue(fruitCount instanceof ConcurrentMap);
        assertEquals(3, fruitCount.get(APPLE.name));
    }

    @Test
    void demoFlatMap() throws IOException {
        Path path = Paths.get(this.getClass().getResource("/demo-flat-map.txt").getPath());
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        Stream<String> words = lines.flatMap(line -> Stream.of(line.split(ANY_WHITESPACE)));

        assertEquals(Arrays.asList("1a", "1b", "2a", "2b"), words.collect(Collectors.toList()));
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
