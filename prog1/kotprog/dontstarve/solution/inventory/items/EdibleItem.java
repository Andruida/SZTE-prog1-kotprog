package prog1.kotprog.dontstarve.solution.inventory.items;

public abstract class EdibleItem extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   a tárgy típusa
     * @param amount az item mennyisége
     */
    public EdibleItem(ItemType type, int maxStackAmount, int amount) {
        super(type, maxStackAmount, amount);
    }

    /**
     * Megadja, hogy mennyi életerőt ad a tárgy.
     * @return az életerő mennyisége
     */
    public abstract int getHealth();

    /**
     * Megadja, hogy mennyi éhséget csökkent a tárgy.
     * @return az éhség csökkentés mennyisége
     */
    public abstract int getHunger();
}
