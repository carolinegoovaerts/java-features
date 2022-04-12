package ch.sourcequality.poc.java_seventeen.jep409;

abstract sealed class RedBlueGreen permits Red, Blue, Green {

    @Override
    public int hashCode() {
        return simpleName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return simpleName().equals(obj.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return simpleName();
    }

    private String simpleName() {
        return this.getClass().getSimpleName();
    }
}
