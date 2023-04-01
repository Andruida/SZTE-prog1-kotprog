package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A főtt répa item leírására szolgáló osztály.
 */
public class ItemCookedCarrot extends EdibleItem {

    /**
     * Mennyi életerőt tölt vissza az étel.
     */
    private static final int HEALTH = 3;

    /**
     * Mennyi éhséget tölt vissza az étel.
     */
    private static final int HUNGER = 10;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemCookedCarrot(int amount) {
        super(ItemType.COOKED_CARROT, 10, amount);
        health = HEALTH;
        hunger = HUNGER;
    }
}
