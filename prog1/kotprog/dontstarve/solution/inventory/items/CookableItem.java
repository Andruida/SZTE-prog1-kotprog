package prog1.kotprog.dontstarve.solution.inventory.items;

public abstract class CookableItem extends EdibleItem {

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type   a tárgy típusa
     * @param amount az item mennyisége
     */
    public CookableItem(ItemType type, int amount) {
        super(type, amount);
    }

    /**
     * Visszaadja a tárgy a főtt változatávát. [másolat]
     * 
     * @return a főtt tárgy
     */
    public abstract EdibleItem cook();
}
