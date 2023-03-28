package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;

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
        executor.getInventory().cookItem(index);
        super.execute(executor);
    }
}
