package ch.sourcequality.poc.jigsaw;

import java.util.stream.Stream;

public class BooksOverview {

    public Stream<Book> getAll() {
        return Stream.of(new Book());
    }
}
