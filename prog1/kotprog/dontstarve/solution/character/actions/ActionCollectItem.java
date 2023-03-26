package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.level.MutableField;

/**
 * Az item begyűjtése akció leírására szolgáló osztály: egy item begyűjtése az aktuális mezőről.
 */
public class ActionCollectItem extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionCollectItem() {
        super(ActionType.COLLECT_ITEM);
    }

    @Override
    public void execute(MutableCharacter executor) {
        MutableField field = (MutableField) executor.getCurrentPosition().getNearestField();
        if (field == null) {
            super.execute(executor);
            return;
        }
        AbstractItem item = field.pickupItem();
        boolean success = executor.getInventory().addItem(item);
        if (!success) {
            ((MutableField) executor.getCurrentPosition().getNearestField()).addItem(item);
        }
        super.execute(executor);
    }
}
