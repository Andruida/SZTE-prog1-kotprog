package prog1.kotprog.dontstarve.solution.inventory.items;

public abstract class EdibleItem extends AbstractItem {

    /**
     * Az étel mennyi életerőt ad.
     */
    protected int health;

    /**
     * Az étel mennyi éhséget csökkent.
     */
    protected int hunger;

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
    public int getHealth() {
        return health;
    }

    /**
     * Megadja, hogy mennyi éhséget csökkent a tárgy.
     * @return az éhség csökkentés mennyisége
     */
    public int getHunger() {
        return hunger;
    }
}
