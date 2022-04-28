package ch.sourcequality.poc.jigsaw;

import java.util.stream.Stream;

public class DefaultBooksOverview implements BooksOverview {

    @Override
    public Stream<Book> getAll() {
        return Stream.of(new Book());
    }
}
