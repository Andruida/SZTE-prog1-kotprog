package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;

public interface MutableField extends BaseField {
    void addItem(AbstractItem item);

    AbstractItem pickupItem();
}
