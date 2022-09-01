package Pong;

public class Random {
    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
    public static float randomFloat(float min, float max) {
        return (float) (Math.random() * (max - min + 1)) + min;
    }
}
