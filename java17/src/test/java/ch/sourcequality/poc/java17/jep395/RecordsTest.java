package ch.sourcequality.poc.java17.jep395;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static ch.sourcequality.poc.java17.jep395.Records.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecordsTest {

    private static final Currency CHF = Currency.getInstance("CHF");
    private static final Currency EUR = Currency.getInstance("EUR");

    private static Repository<Product> productRepository(List<Product> products) {
        return new Repository<>() {
            @Override
            public List<Product> getAll() {
                return products;
            }
        };
    }

    @Test
    void demoExternalisation() {
        var point = new Point(1, 2);

        assertEquals(1, point.x());
        assertEquals(2, point.y());
    }

    @Test
    void demoEqualsContract() {
        var origin = new Point(0, 0);
        var anotherOrigin = new Point(0, 0);

        assertEquals(anotherOrigin, origin);
    }

    @Test
    void demoMultipleReturnValueAndCompoundMapKeyAndDtoAndExternalisation() {
        var products = List.of(
                new Product("a", new Price(CHF, BigDecimal.ONE)),
                new Product("b", new Price(EUR, BigDecimal.ONE)));
        var repository = productRepository(products);

        var itemsByPrice = repository.getAll().stream().collect(Collectors.toMap(Product::price, Product::item));
        var itemByPrice = itemsByPrice.get(new Price(CHF, BigDecimal.ONE));

        assertEquals("a", itemByPrice);
    }

    @Test
    void demoInvariantEnforcedByConstructor() {
        Executable createIllegalPoint = () -> new FirstQuadrantPoint(-1, 0);
        assertThrows(IllegalArgumentException.class, createIllegalPoint);
    }

    private interface Repository<T> {
        List<T> getAll();
    }
}
