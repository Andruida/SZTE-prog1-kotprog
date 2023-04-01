package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A főtt bogyó item leírására szolgáló osztály.
 */
public class ItemCookedBerry extends EdibleItem {

    /**
     * Mennyi életerőt tölt vissza az étel.
     */
    private static final int HEALTH = 1;

    /**
     * Mennyi éhséget tölt vissza az étel.
     */
    private static final int HUNGER = 10;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemCookedBerry(int amount) {
        super(ItemType.COOKED_BERRY, 10, amount);
        health = HEALTH;
        hunger = HUNGER;
    }
}
