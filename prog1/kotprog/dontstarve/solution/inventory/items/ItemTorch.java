package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A fáklya item leírására szolgáló osztály.
 */
public class ItemTorch extends EquippableItem {

    /**
     * A tárgy maximális állapota.
     */
    private static final int MAX_DURABILITY = 20;

    /**
     * A tárgy sebzése.
     */
    private static final int DAMAGE = 6;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemTorch() {
        super(ItemType.TORCH);
        durability = MAX_DURABILITY;
        maxDurability = MAX_DURABILITY;
        damage = DAMAGE;
    }
}
