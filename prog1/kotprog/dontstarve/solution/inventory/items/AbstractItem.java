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
     * Az item maximális mennyisége egy slot-ban.
     */
    private final int maxStackAmount;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     * @param type az item típusa
     * @param amount az item mennyisége
     */
    public AbstractItem(ItemType type, int maxStackAmount, int amount) {
        this.type = type;
        this.maxStackAmount = maxStackAmount;
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
        return maxStackAmount;
    }

    @Override
    public abstract AbstractItem clone();

    // @Override
    // public boolean equals(Object obj) {
    //     if (obj == null) {
    //         return false;
    //     }
    //     if (obj == this) {
    //         return true;
    //     }
    //     if (!(obj instanceof AbstractItem)) {
    //         return false;
    //     }
    //     AbstractItem item = (AbstractItem) obj;
    //     return item.getType() == getType() && item.getAmount() == getAmount();
    // }

}
