package ch.sourcequality.poc.java17.jep378;

@SuppressWarnings("TextBlockMigration")
final class TextBlocks {

    static final String ONE_DIMENSIONAL_SQL = "SELECT * FROM track t WHERE\n" +
            "t.title LIKE CONCAT(\"%%\", %1$s, \"%%\")\n" +
            "ORDER BY t.title";

    static final String TWO_DIMENSIONAL_SQL = """
            SELECT * FROM track t WHERE
            t.title LIKE CONCAT("%%", %1$s, "%%")
            ORDER BY t.title""";

    static final String TWO_DIMENSIONAL_YAML = """
            numbers:
                one: 1
                two: 2
                three: 3
            """;
}
