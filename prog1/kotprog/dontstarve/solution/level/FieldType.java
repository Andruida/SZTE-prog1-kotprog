package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemLog;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemStone;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;

public enum FieldType {
    EMPTY,
    WATER,
    TREE,
    STONE,
    BERRY,
    CARROT,
    TWIG;

    public AbstractItem getLoot() {
        switch (this) {
            case STONE:
                return new ItemStone(3);
            case TREE:
                return new ItemLog(2);
            case BERRY:
                return new ItemRawBerry(1);
            case CARROT:
                return new ItemRawCarrot(1);
            case TWIG:
                return new ItemTwig(1);
            default:
                return null;
        }
    }
}
