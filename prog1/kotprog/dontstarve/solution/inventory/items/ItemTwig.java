package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A gally item leírására szolgáló osztály.
 */
public class ItemTwig extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemTwig(int amount) {
        super(ItemType.TWIG, 20, amount);
    }

    @Override
    public ItemTwig clone() {
        return new ItemTwig(getAmount());
    }
}
