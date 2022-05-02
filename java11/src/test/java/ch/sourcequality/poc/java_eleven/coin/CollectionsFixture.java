package ch.sourcequality.poc.java_eleven.coin;

import java.util.Arrays;
import java.util.Collection;

class CollectionsFixture {

    @SafeVarargs
    private <T> void innerAddAll(Collection<? super T> collection, T... elements) {
        collection.addAll(Arrays.asList(elements));
    }

    @SafeVarargs
    final <T> void addAll(Collection<? super T> collection, T... elements) {
        innerAddAll(collection, elements);
    }
}
