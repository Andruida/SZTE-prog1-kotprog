package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EdibleItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.character.Character;

/**
 * Az étel elfogyasztása akció leírására szolgáló osztály: egy étel elfogyasztása az inventory-ból.
 */
public class ActionEat extends Action {
    /**
     * A megenni kívánt étel pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index a megenni kívánt étel pozíciója az inventory-ban
     */
    public ActionEat(int index) {
        super(ActionType.EAT);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return a megenni kívánt étel pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void execute(MutableCharacter executor) {
        if (executor.getHunger() >= Character.MAX_HUNGER) {
            super.execute(executor);
            return;
        }
        AbstractItem item = executor.getInventory().getItem(index);
        if (item == null) {
            super.execute(executor);
            return;
        }
        ItemType type = executor.getInventory().eatItem(index);
        if (type != null) {
            EdibleItem food = (EdibleItem)item;
            executor.addHp(food.getHealth());
            executor.addHunger(food.getHunger());
        }

        super.execute(executor);
    }
}
