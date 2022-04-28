package ch.sourcequality.poc.java_eleven.jigsaw;

import ch.sourcequality.poc.jigsaw.Book;
import ch.sourcequality.poc.jigsaw.DefaultBooksOverview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class BooksOverviewIT {

    @Test
    void shouldReturnBooks() {
        DefaultBooksOverview overview = new DefaultBooksOverview();
        List<Book> books = overview.getAll().collect(Collectors.toList());

        Assertions.assertEquals(1, books.size());
    }
}
