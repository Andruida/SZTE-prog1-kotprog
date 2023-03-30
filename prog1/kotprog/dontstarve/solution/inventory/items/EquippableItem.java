package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * Felvehető / kézbe vehető item leírására szolgáló osztály.
 */
public abstract class EquippableItem extends AbstractItem {

    /**
     * A tárgy állapota.
     */
    protected int durability;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   az item típusa
     */
    public EquippableItem(ItemType type) {
        super(type, 1, 1);
    }

    /**
     * Megadja, hogy milyen állapotban van a tárgy.
     * @return a tárgy használatlansága, %-ban (100%: tökéletes állapot)
     */
    public abstract float percentage();

    @Override
    public int getMaxStackAmount() {
        return 1;
    }

    /**
     * Koptatja a tárgyat.
     * @return true, ha a tárgy használhatatlanná vált, false egyébként
     */
    public boolean damage() {
        durability--;
        if (durability < 0) {
            durability = 0;
        }
        return durability <= 0;
    }
}
