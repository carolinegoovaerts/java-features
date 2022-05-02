package ch.sourcequality.poc.java_eleven.coin;

import java.util.HashMap;
import java.util.Map;

interface Index {

    // since JDK 9
    private Map<String, Object> newMap() {
        return new HashMap<>();
    }

    default Map<String, Object> cleanAdd(String s, Object o) {
        Map<String, Object> map = this.newMap();
        map.put(s, o);
        return map;
    }

    default Map<String, Object> clean() {
        return this.newMap();
    }
}
