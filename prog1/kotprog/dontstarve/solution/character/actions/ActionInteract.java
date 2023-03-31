package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.MutableField;

/**
 * Az aktuális mezőn lévő tereptárggyal való interakcióba lépés (favágás, kőcsákányozás, gally / bogyó / répa leszedése)
 * leírására szolgáló osztály.
 */
public class ActionInteract extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionInteract() {
        super(ActionType.INTERACT);
    }

    @Override
    public void execute(MutableCharacter character) {
        MutableField field = (MutableField)character.getCurrentPosition().getNearestField();
        if (field == null) {
            super.execute(character);
            return;
        }
        EquippableItem tool = character.getInventory().equippedItem();
        AbstractItem item = field.interact(tool);
        if (tool != null && tool.percentage() <= 0) {
            character.getInventory().unequipItem();
        }
        if (item == null) { 
            super.execute(character);
            return;
        }
        if (item.getType() == ItemType.LOG || item.getType() == ItemType.STONE) {
            field.addItem(item);
            super.execute(character);
            return;
        }

        boolean success = character.getInventory().addItem(item);
        if (!success) {
            field.addItem(item);
        }
        super.execute(character);
    }
}
