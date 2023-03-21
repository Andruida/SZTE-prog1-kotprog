package prog1.kotprog.dontstarve.solution.inventory.items;

public abstract class EdibleItem extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   a tárgy típusa
     * @param amount az item mennyisége
     */
    public EdibleItem(ItemType type, int amount) {
        super(type, amount);
    }
}
