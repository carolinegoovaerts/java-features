package ch.sourcequality.poc.jigsaw;

import java.util.stream.Stream;

public interface BooksOverview {
    Stream<Book> getAll();
}
