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
     * A tárgy maximális állapota.
     */
    protected int maxDurability;

    /**
     * A tárgy sebzése.
     */
    protected int damage;

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
    public float percentage() {
        return (float)durability / maxDurability * 100;
    }

    @Override
    public int getMaxStackAmount() {
        return 1;
    }

    /**
     * Megadja, hogy a tárgy mennyi sebzést okoz.
     * @return a tárgy sebzése
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Koptatja a tárgyat.
     * @return true, ha a tárgy használhatatlanná vált, false egyébként
     */
    public boolean wear() {
        durability--;
        if (durability < 0) {
            durability = 0;
        }
        return durability <= 0;
    }

    @Override
    public EquippableItem clone() {
        EquippableItem item = (EquippableItem)super.clone();
        item.durability = durability;
        return item;
    }
}
