package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A lándzsa item leírására szolgáló osztály.
 */
public class ItemSpear extends EquippableItem {

    /**
     * A tárgy maximális állapota.
     */
    private static final int MAX_DURABILITY = 10;

    /**
     * A tárgy sebzése.
     */
    private static final int DAMAGE = 19;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemSpear() {
        super(ItemType.SPEAR);
        durability = MAX_DURABILITY;
        maxDurability = MAX_DURABILITY;
        damage = DAMAGE;
    }

}
