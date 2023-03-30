package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers répa item leírására szolgáló osztály.
 */
public class ItemRawCarrot extends CookableItem {

    /**
     * Mennyi életerőt tölt vissza az étel.
     */
    private static final int HEALTH = 1;

    /**
     * Mennyi éhséget tölt vissza az étel.
     */
    private static final int HUNGER = 12;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawCarrot(int amount) {
        super(ItemType.RAW_CARROT, 10, amount);
    }

    @Override
    public EdibleItem cook() {
        return new ItemCookedCarrot(getAmount());
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
