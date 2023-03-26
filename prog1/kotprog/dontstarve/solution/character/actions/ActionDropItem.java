package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.level.MutableField;

/**
 * Az item eldobás akció leírására szolgáló osztály: egy inventory-ban lévő item eldobása az aktuális mezőre.
 */
public class ActionDropItem extends Action {
    /**
     * Az eldobandó tárgy pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index az eldobandó tárgy pozíciója az inventory-ban
     */
    public ActionDropItem(int index) {
        super(ActionType.DROP_ITEM);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return az eldobandó tárgy pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void execute(MutableCharacter executor) {
        AbstractItem item = executor.getInventory().dropItem(index);
        MutableField field = (MutableField)executor.getCurrentPosition().getNearestField();
        if (field == null) {
            super.execute(executor);
            return;
        }
        field.addItem(item);
        super.execute(executor);
    }
}
