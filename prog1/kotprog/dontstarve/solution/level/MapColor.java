package prog1.kotprog.dontstarve.solution.level;

/**
 * A betöltendő mapen előforduló színek enumja.
 */

public enum MapColor {
    EMPTY(MapColors.EMPTY),
    WATER(MapColors.WATER),
    TREE(MapColors.TREE),
    STONE(MapColors.STONE),
    TWIG(MapColors.TWIG),
    BERRY(MapColors.BERRY),
    CARROT(MapColors.CARROT);

    private final int color;

    MapColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public static MapColor fromInt(int color) {
        for (MapColor c : values()) {
            if (c.color == color) {
                return c;
            }
        }
        return null;
    }

}
