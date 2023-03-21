package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A főtt répa item leírására szolgáló osztály.
 */
public class ItemCookedCarrot extends EdibleItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemCookedCarrot(int amount) {
        super(ItemType.COOKED_CARROT, amount);
    }

    @Override
    public int getMaxStackAmount() {
        return 10;
    }

    @Override
    public ItemCookedCarrot clone() {
        return new ItemCookedCarrot(getAmount());
    }
}
