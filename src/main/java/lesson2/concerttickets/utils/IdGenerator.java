package lesson2.concerttickets.utils;

import java.util.Random;

public class IdGenerator {

    public static Long generate() {
        return new Random().nextLong(1L,1000L);
    }
}
