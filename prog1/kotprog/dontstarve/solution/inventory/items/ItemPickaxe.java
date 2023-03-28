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
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemPickaxe() {
        super(ItemType.PICKAXE);
    }

    @Override
    public float percentage() {
        return (float)durability / MAX_DURABILITY * 100;
    }
}
