package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A csákány item leírására szolgáló osztály.
 */
public class ItemPickaxe extends EquippableItem {

    /**
     * A tárgy maximális állapota.
     */
    private static final int MAX_DURABILITY = 30;

    /**
     * A tárgy sebzése.
     */
    private static final int DAMAGE = 8;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemPickaxe() {
        super(ItemType.PICKAXE);
        durability = MAX_DURABILITY;
        maxDurability = MAX_DURABILITY;
        damage = DAMAGE;
    }
}
