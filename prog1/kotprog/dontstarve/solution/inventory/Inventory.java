package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.*;

public class Inventory implements BaseInventory {

    /**
     * Az inventory slotjai
     */
    private AbstractItem[] slots;

    /**
     * A kézben tartott tárgy
     */
    private EquippableItem equippedItem;

    /**
     * Konstruktor
     */
    public Inventory() {
        slots = new AbstractItem[10];
        equippedItem = null;
    }

    @Override
    public boolean addItem(final AbstractItem itemRef) {
        if (itemRef == null) {
            return false;
        }

        AbstractItem item = itemRef.clone();
        if (item.getAmount() <= 0) {
            return true;
        }

        // van-e ugyanolyan típusú item?
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null || slots[i].getType() != item.getType() ||
                slots[i].getAmount() == slots[i].getMaxStackAmount()
            ) {
                continue;
            }

            if (slots[i].getAmount() + item.getAmount() <= slots[i].getMaxStackAmount()) {
                slots[i].addAmount(item.getAmount());
                itemRef.setAmount(0);
                return true;
            }

            int amount = slots[i].getMaxStackAmount() - slots[i].getAmount();
            slots[i].addAmount(amount);
            AbstractItem newItem = item.clone();
            newItem.addAmount(-amount);
            itemRef.setAmount(newItem.getAmount());
            return this.addItem(itemRef);
        }

        // van-e üres slot?
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                continue;
            }
            if (item.getAmount() <= item.getMaxStackAmount()) {
                slots[i] = item;
                itemRef.setAmount(0);
                return true;
            }
            int amount = item.getMaxStackAmount();
            AbstractItem newItem = item.clone();
            item.setAmount(amount);
            slots[i] = item;
            newItem.addAmount(-amount);
            itemRef.setAmount(newItem.getAmount());
            return this.addItem(itemRef);
        }

        return false;
    }

    @Override
    public AbstractItem dropItem(int index) {
        if (indexInvalidOrNull(index)) {
            return null;
        }
        AbstractItem droppedItem = slots[index];
        slots[index] = null;
        return droppedItem;
    }

    @Override
    public boolean removeItem(ItemType type, int amount) {
        if (type == null) {
            return false;
        }
        if (amount <= 0) {
            return true;
        }
        if (this.getItemAmount(type) < amount) {
            return false;
        }

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null || slots[i].getType() != type) {
                continue;
            }

            if (slots[i].getAmount() > amount) {
                slots[i].addAmount(-amount);
                return true;
            }

            amount -= slots[i].getAmount();
            slots[i] = null;
            if (amount == 0) {
                return true;
            }
        }
        throw new RuntimeException("Ennek nem lenne szabad előfordulnia!");
    }

    @Override
    public boolean swapItems(int index1, int index2) {
        if (indexInvalidOrNull(index1) || indexInvalidOrNull(index2)) {
            return false;
        }
        AbstractItem temp = slots[index1];
        slots[index1] = slots[index2];
        slots[index2] = temp;
        return true;
    }

    @Override
    public boolean moveItem(int index, int newIndex) {
        if (indexInvalidOrNull(index) || indexInvalidOrNOTNull(newIndex)) {
            return false;
        }
        slots[newIndex] = slots[index];
        slots[index] = null;
        return true;
    }

    @Override
    public boolean combineItems(int index1, int index2) {
        if (indexInvalidOrNull(index1) || indexInvalidOrNull(index2) || index1 == index2) {
            return false;
        }
        if (slots[index1].getType() != slots[index2].getType()) {
            return false;
        }
        if (slots[index1].getAmount() == slots[index1].getMaxStackAmount()) {
            return false;
        }
        if (slots[index1].getMaxStackAmount() == 1 || slots[index2].getMaxStackAmount() == 1) {
            return false;
        }

        int sum = slots[index1].getAmount() + slots[index2].getAmount();
        if (sum <= slots[index1].getMaxStackAmount()) {
            slots[index1].addAmount(slots[index2].getAmount());
            slots[index2] = null;
            return true;
        }

        int amount = slots[index1].getMaxStackAmount() - slots[index1].getAmount();
        slots[index1].addAmount(amount);
        slots[index2].addAmount(-amount);
        return true;
    }

    @Override
    public boolean equipItem(int index) {
        if (indexInvalidOrNull(index)) {
            return false;
        }
        if (!(slots[index] instanceof EquippableItem)) {
            return false;
        }

        EquippableItem item = (EquippableItem)slots[index];
        if (equippedItem != null) {
            slots[index] = equippedItem;
        } else {
            slots[index] = null;
        }

        equippedItem = item;
        return true;
    }

    @Override
    public EquippableItem unequipItem() {
        EquippableItem item = equippedItem;
        equippedItem = null;
        if (addItem(item)) {
            return null;
        }
        return item;
    }

    private ItemType consumeItem(int index, boolean cook) {
        if (indexInvalidOrNull(index)) {
            return null;
        }
        if (cook && !(slots[index] instanceof CookableItem)) {
            return null;
        }
        if (!cook && !(slots[index] instanceof EdibleItem)) {
            return null;
        }

        ItemType type = slots[index].getType();
        slots[index].addAmount(-1);
        if (slots[index].getAmount() <= 0) {
            slots[index] = null;
        }

        return type;
    }

    @Override
    public ItemType cookItem(int index) {
        return consumeItem(index, true);
    }

    @Override
    public ItemType eatItem(int index) {
        return consumeItem(index, false);
    }

    @Override
    public int emptySlots() {
        int emptySlotsAmount = 0;
        for (AbstractItem slot : slots) {
            if (slot == null) {
                emptySlotsAmount++;
            }
        }
        return emptySlotsAmount;
    }

    @Override
    public EquippableItem equippedItem() {
        return equippedItem;
    }

    @Override
    public AbstractItem getItem(int index) {
        if (index < 0 || index >= slots.length) {
            return null;
        }
        return slots[index];
    }

    public int getItemAmount(ItemType type) {
        int amount = 0;
        for (AbstractItem slot : slots) {
            if (slot == null || slot.getType() != type) {
                continue;
            }
            amount += slot.getAmount();
        }
        return amount;
    }

    private boolean indexInvalidOrNull(int index) {
        return index < 0 || index >= slots.length || slots[index] == null;
    }
    private boolean indexInvalidOrNOTNull(int index) {
        return index < 0 || index >= slots.length || slots[index] != null;
    }

}
