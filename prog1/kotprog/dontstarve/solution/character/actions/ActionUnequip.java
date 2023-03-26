package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;

/**
 * A tárgy levétele akció leírására szolgáló osztály: az aktuálisan kézben lévő item visszarakása az inventory-ba.
 */
public class ActionUnequip extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionUnequip() {
        super(ActionType.UNEQUIP);
    }

    @Override
    public void execute(MutableCharacter executor) {
        executor.getInventory().unequipItem();
        super.execute(executor);
    }
}
