package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers bogyó item leírására szolgáló osztály.
 */
public class ItemRawBerry extends CookableItem {

    /**
     * Mennyi életerőt tölt vissza az étel.
     */
    private static final int HEALTH = -5;

    /**
     * Mennyi éhséget tölt vissza az étel.
     */
    private static final int HUNGER = 20;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawBerry(int amount) {
        super(ItemType.RAW_BERRY, 10, amount);
    }

    @Override
    public EdibleItem cook() {
        return new ItemCookedBerry(getAmount());
    }

    @Override
    public int getHealth() {
        return HEALTH;
    }

    @Override
    public int getHunger() {
        return HUNGER;
    }
}
