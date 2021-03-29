package ipvc.estg.prog2.misc;

import java.util.Random;

public class RandomSingleton {

    private static Random rnd = new Random();

    public static double nextDouble() {
        return rnd.nextDouble();
    }

    public static int nextInt() {
        return rnd.nextInt();
    }

    public static int nextInt(int bound) {
        return rnd.nextInt(bound);
    }

    public static long nextLong() {
        return rnd.nextLong();
    }

    public static boolean nextBoolean() {
        return rnd.nextBoolean();
    }

    public static float nextFloat() {
        return rnd.nextFloat();
    }
}
