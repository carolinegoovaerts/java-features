package ch.sourcequality.poc.java_eleven.jigsaw;

import ch.sourcequality.poc.jigsaw.Book;
import ch.sourcequality.poc.jigsaw.BooksOverview;
import ch.sourcequality.poc.jigsaw.DefaultBooksOverview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class BooksOverviewIT {

    @Test
    void shouldReturnBooks() {
        BooksOverview overview = new DefaultBooksOverview();
        List<Book> books = overview.getAll().collect(Collectors.toList());

        Assertions.assertEquals(1, books.size());
    }
}
