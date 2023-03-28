package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers bogyó item leírására szolgáló osztály.
 */
public class ItemRawBerry extends CookableItem {
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
}
