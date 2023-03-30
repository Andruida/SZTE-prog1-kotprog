package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.CookableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.MutableField;

/**
 * A főzés akció leírására szolgáló osztály: egy item megfőzése.
 */
public class ActionCook extends Action {
    /**
     * A megfőzni kívánt tárgy pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index a megfőzni kívánt tárgy pozíciója az inventory-ban
     */
    public ActionCook(int index) {
        super(ActionType.COOK);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return a megfőzni kívánt tárgy pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void execute(MutableCharacter executor) {
        if (!executor.getCurrentPosition().getNearestField().hasFire()) {
            super.execute(executor);
            return;
        }
        ItemType type = executor.getInventory().cookItem(index);
        if (type == null) {
            super.execute(executor);
            return;
        }
        AbstractItem item = ((CookableItem)type.instantiate()).cook();
        boolean success = executor.getInventory().addItem(item);

        if (!success) {
            ((MutableField)executor.getCurrentPosition().getNearestField()).addItem(item);
        }
        super.execute(executor);
    }
}
