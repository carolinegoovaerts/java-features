package ch.sourcequality.poc.java8.datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class DateAndTimeTest {

    @Test
    void dayOfWeek() {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY.plus(4);
        assertEquals(DayOfWeek.FRIDAY, dayOfWeek);
    }

    @Test
    void displayName() {
        String displayName = Month.APRIL.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        assertEquals("Apr", displayName);
    }

    @Test
    void temporalAdjuster() {
        LocalDate localDate = LocalDate.of(2022, Month.JANUARY, 1);
        TemporalAdjuster nextFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);

        assertEquals(LocalDate.of(2022, Month.JANUARY, 7), localDate.with(nextFriday));
    }

    @Test
    void leapYearByYearMonth() {
        YearMonth february2020 = YearMonth.of(2020, Month.FEBRUARY);
        YearMonth april2020 = YearMonth.of(2020, Month.APRIL);

        assertEquals(29, february2020.lengthOfMonth());
        assertTrue(february2020.isLeapYear());
        assertTrue(april2020.isLeapYear());
    }

    @Test
    void leapYearByMonthDay() {
        MonthDay february29th = MonthDay.of(Month.FEBRUARY, 29);
        assertFalse(february29th.isValidYear(2021));
    }

    @Test
    void leapYearByYear() {
        Year twoThousand = Year.of(2000);
        assertTrue(twoThousand.isLeap());
    }
}
