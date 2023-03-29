package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
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
        AbstractItem item = field.interact();
        if (item != null) {
            boolean success = character.getInventory().addItem(item);
            if (!success) {
                field.addItem(item);
            }
        }
        super.execute(character);
    }
}
