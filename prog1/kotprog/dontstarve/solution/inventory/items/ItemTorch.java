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
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemTorch() {
        super(ItemType.TORCH);
        durability = MAX_DURABILITY;
    }

    @Override
    public float percentage() {
        return (float)durability / MAX_DURABILITY * 100;
    }
}
