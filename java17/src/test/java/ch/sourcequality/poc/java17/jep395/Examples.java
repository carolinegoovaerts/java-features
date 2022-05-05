package ch.sourcequality.poc.java17.jep395;

import java.math.BigDecimal;
import java.util.Currency;

class Examples {

    record Point(int x, int y) {
    }

    record FirstQuadrantPoint(int x, int y) {
        FirstQuadrantPoint {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    record Product(String item, Price price) {
    }

    record Price(Currency currency, BigDecimal value) {
    }
}
