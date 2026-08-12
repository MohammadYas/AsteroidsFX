package dk.sdu.mmmi.mmy.common.data;

public class GameKeys {

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;
    public static final int DOWN = 4;

    private static final int NUM_KEYS = 5;

    private final boolean[] keys = new boolean[NUM_KEYS];

    public void setKey(int key, boolean pressed) {
        keys[key] = pressed;
    }

    public boolean isDown(int key) {
        return keys[key];
    }
}
