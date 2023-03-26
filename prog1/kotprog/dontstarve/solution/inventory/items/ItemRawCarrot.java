package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers répa item leírására szolgáló osztály.
 */
public class ItemRawCarrot extends CookableItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawCarrot(int amount) {
        super(ItemType.RAW_CARROT, 10, amount);
    }

    @Override
    public ItemRawCarrot clone() {
        return new ItemRawCarrot(getAmount());
    }

    @Override
    public EdibleItem cook() {
        return new ItemCookedCarrot(getAmount());
    }
}
