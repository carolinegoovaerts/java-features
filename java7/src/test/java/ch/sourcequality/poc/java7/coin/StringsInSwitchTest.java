package ch.sourcequality.poc.java7.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringsInSwitchTest {

    private static String theColorOf(String fruit) {
        switch (fruit) {
            case "apple":
                return "green";
            case "banana":
            case "citron":
                return "yellow";
            default:
                throw new IllegalArgumentException();
        }
    }

    @Test
    void shouldReturnTheExpectedColor() {
        String fruit = "citron";
        String color = theColorOf(fruit);
        Assertions.assertEquals("yellow", color);
    }
}
