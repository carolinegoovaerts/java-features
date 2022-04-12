module ch.sourcequality.poc.java_eleven {
    requires org.junit.jupiter.api;
    opens ch.sourcequality.poc.java11.jigsaw to org.junit.platform.commons;
}
