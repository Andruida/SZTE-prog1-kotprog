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
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemAxe() {
        super(ItemType.AXE);
    }

    @Override
    public float percentage() {
        return (float)durability / MAX_DURABILITY * 100;
    }
}
