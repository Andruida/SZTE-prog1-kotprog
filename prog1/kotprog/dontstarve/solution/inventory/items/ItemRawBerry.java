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
        super(ItemType.RAW_BERRY, amount);
    }

    @Override
    public int getMaxStackAmount() {
        return 10;
    }

    @Override
    public ItemRawBerry clone() {
        return new ItemRawBerry(getAmount());
    }

    @Override
    public EdibleItem cook() {
        return new ItemCookedBerry(getAmount());
    }
}
