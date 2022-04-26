module ch.sourcequality.poc.java_eleven.jigsaw {
    requires ch.sourcequality.poc.jigsaw;
    requires org.junit.jupiter.api;
    opens ch.sourcequality.poc.java_eleven.jigsaw to org.junit.platform.commons;
}
