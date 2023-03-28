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
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemSpear() {
        super(ItemType.SPEAR);

    }

    @Override
    public float percentage() {
        return (float)durability / MAX_DURABILITY * 100;
    }

}
