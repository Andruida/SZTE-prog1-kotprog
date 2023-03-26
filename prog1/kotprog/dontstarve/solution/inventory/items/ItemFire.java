package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A fire item leírására szolgáló osztály.
 */
public class ItemFire extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemFire() {
        super(ItemType.FIRE, 1, 1);
    }

    @Override
    public ItemFire clone() {
        return new ItemFire();
    }
}
