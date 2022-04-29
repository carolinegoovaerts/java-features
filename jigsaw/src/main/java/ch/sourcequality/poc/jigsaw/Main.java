package ch.sourcequality.poc.jigsaw;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) {
        BooksOverview overview = new DefaultBooksOverview();
        List<Book> books = overview.getAll().collect(Collectors.toList());

        System.out.println(books);
    }
}
