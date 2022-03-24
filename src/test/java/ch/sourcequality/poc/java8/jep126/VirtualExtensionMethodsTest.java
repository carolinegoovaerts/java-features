package ch.sourcequality.poc.java8.jep126;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VirtualExtensionMethodsTest {
    private static final String USE_LONGS = "use longs";
    private static final String CAN_T_FLY_YET = "can't fly yet";
    private static final String FLAP_WINGS = "flap wings";
    private static final String I_COULD_FLY_IF_I_WANTED_TO = "I could fly if I wanted to";
    private static final String USE_GILLS = "use gills";
    public static final String FAN_FINS = "fan fins";
    public static final String USE_LONGS_AND_AIR_SACS = "use longs and air sacs";

    @Test
    void shouldReturnExpectedResponse() {
        Animal human = new Human();
        String response = human.breathe();
        assertEquals(USE_LONGS, response);
    }

    @Test
    void shouldReturnDefaultResponseWhenNotImplemented() {
        Animal human = new Human();
        String response = human.fly();
        assertEquals(CAN_T_FLY_YET, response);
    }

    @Test
    void redeclareDefaultMethod() {
        // implement the remaining abstract method using a lambda expression
        Animal fish = (Fish) () -> I_COULD_FLY_IF_I_WANTED_TO;
        String response = fish.fly();

        assertEquals(I_COULD_FLY_IF_I_WANTED_TO, response);
    }

    @Test
    void redefineDefaultMethod() {
        Animal bird = new Bird();
        String response = bird.fly();
        assertEquals(FLAP_WINGS, response);
    }

    @Test
    void invokeStaticInterfaceMethod() {
        Animal flyingFish = Fish.flyingFish();
        assertEquals(FAN_FINS, flyingFish.fly());
    }

    private interface Animal {
        String breathe();
        default String fly() {
            return CAN_T_FLY_YET;
        }
    }

    private static final class Human implements Animal {
        @Override
        public String breathe() {
            return USE_LONGS;
        }
    }

    private static final class Bird implements Animal {
        @Override
        public String breathe() {
            return USE_LONGS_AND_AIR_SACS;
        }

        @Override
        public String fly() {
            return FLAP_WINGS;
        }
    }

    private interface Fish extends Animal {
        static Fish flyingFish() {
            return () -> FAN_FINS;
        }

        @Override
        default String breathe() {
            return USE_GILLS;
        }

        @Override
        String fly(); // redeclare default method
    }
}
