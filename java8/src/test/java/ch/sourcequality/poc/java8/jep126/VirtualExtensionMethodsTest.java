package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualExtensionMethodsTest {

    @Test
    void shouldReturnExpectedResponse() {
        Animal human = new Human();
        String response = human.breathe();
        assertEquals(Human.USE_LONGS, response);
    }

    @Test
    void shouldReturnDefaultResponseWhenNotImplemented() {
        Animal human = new Human();
        String response = human.fly();
        assertEquals(Animal.CAN_T_FLY_YET, response);
    }

    @Test
    void redeclareDefaultMethod() {
        // implement the remaining abstract method using a lambda expression
        String expected = "I could fly if I wanted to";
        Animal fish = (Fish) () -> expected;
        String response = fish.fly();

        assertEquals(expected, response);
    }

    @Test
    void redefineDefaultMethod() {
        Animal bird = new Bird();
        String response = bird.fly();
        assertEquals(Bird.FLAP_WINGS, response);
    }

    @Test
    void invokeStaticInterfaceMethod() {
        Animal flyingFish = Fish.flyingFish();
        assertEquals(Fish.FAN_FINS, flyingFish.fly());
    }

    private interface Animal {
        String CAN_T_FLY_YET = "can't fly yet";

        String breathe();

        default String fly() {
            return CAN_T_FLY_YET;
        }
    }

    private interface Fish extends Animal {
        String FAN_FINS = "fan fins";
        String USE_GILLS = "use gills";

        static Fish flyingFish() {
            return () -> FAN_FINS;
        }

        @Override
        default String breathe() {
            return USE_GILLS;
        }

        // redeclare default method
        @Override
        String fly();
    }

    private static final class Human implements Animal {
        private static final String USE_LONGS = "use longs";

        @Override
        public String breathe() {
            return USE_LONGS;
        }
    }

    private static final class Bird implements Animal {
        private static final String FLAP_WINGS = "flap wings";
        private static final String USE_LONGS_AND_AIR_SACS = "use longs and air sacs";

        @Override
        public String breathe() {
            return USE_LONGS_AND_AIR_SACS;
        }

        @Override
        public String fly() {
            return FLAP_WINGS;
        }
    }
}
