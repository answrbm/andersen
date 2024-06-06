package lesson2.concerttickets.utils;

import java.util.Random;

public class IdGenerator {

    public static String generate() {
        return Integer.toString(new Random().nextInt(1,1000)) +
                ((char)new Random().nextInt(65,91));
    }
}
