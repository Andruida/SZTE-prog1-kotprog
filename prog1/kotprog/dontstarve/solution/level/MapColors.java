package prog1.kotprog.dontstarve.solution.level;

/**
 * A betöltendő mapen előforduló színek enumja.
 */

public enum MapColors {
    EMPTY(0xFF32C832),
    WATER(0xFF3264C8),
    TREE(0xFFC86432),
    STONE(0xFFC8C8C8),
    TWIG(0xFFF0B478),
    BERRY(0xFFFF0000),
    CARROT(0xFFFAC800);

    private final int color;

    MapColors(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public static MapColors fromInt(int color) {
        for (MapColors c : values()) {
            if (c.color == color) {
                return c;
            }
        }
        return null;
    }

}
