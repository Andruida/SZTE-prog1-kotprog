package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers répa item leírására szolgáló osztály.
 */
public class ItemRawCarrot extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawCarrot(int amount) {
        super(ItemType.RAW_CARROT, amount);
    }

    @Override
    public int getMaxStackAmount() {
        return 10;
    }

    @Override
    public ItemRawCarrot clone() {
        return new ItemRawCarrot(getAmount());
    }
}
