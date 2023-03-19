package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * Egy általános itemet leíró osztály.
 */
public abstract class AbstractItem implements Cloneable {

    /**
     * Az item típusa.
     * @see ItemType
     */
    private final ItemType type;

    /**
     * Az item mennyisége.
     */
    private int amount;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     * @param type az item típusa
     * @param amount az item mennyisége
     */
    public AbstractItem(ItemType type, int amount) {
        this.type = type;
        setAmount(amount);
    }

    /**
     * A type gettere.
     * @return a tárgy típusa
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Az amount gettere.
     * @return a tárgy mennyisége
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Az amount settere.
     * @param amount a tárgy mennyisége
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Növeli az item mennyiségét.
     * @param amount a hozzáadandó mennyiség
     */
    public void addAmount(int amount) {
        setAmount(this.getAmount() + amount);
    }

    /**
     * Megadja az item maximális mennyiségét egy slot-ban.
     * @return az item maximális mennyisége egy slot-ban
     */
    public int getMaxStackAmount() {
        return 99;
    }

    @Override
    public abstract AbstractItem clone();

}
