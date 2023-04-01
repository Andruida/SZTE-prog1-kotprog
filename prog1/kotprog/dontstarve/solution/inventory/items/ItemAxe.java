package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A fejsze item leírására szolgáló osztály.
 */
public class ItemAxe extends EquippableItem {

    /**
     * A tárgy maximális állapota.
     */
    private static final int MAX_DURABILITY = 40;

    /**
     * A tárgy sebzése.
     */
    private static final int DAMAGE = 8;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemAxe() {
        super(ItemType.AXE);
        durability = MAX_DURABILITY;
        maxDurability = MAX_DURABILITY;
        damage = DAMAGE;
    }
}
