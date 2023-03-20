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
    public boolean addItem(final AbstractItem _item) {
        if (_item == null) {
            return false;
        }

        AbstractItem item = _item.clone();
        if (item.getAmount() <= 0) {
            return true;
        }

        // van-e ugyanolyan típusú item?
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null || slots[i].getType() != item.getType() || 
                slots[i].getAmount() == slots[i].getMaxStackAmount()) {
                    continue;
            }
            
            if (slots[i].getAmount() + item.getAmount() <= slots[i].getMaxStackAmount()) {
                slots[i].addAmount(item.getAmount());
                _item.setAmount(0);
                return true;
            }

            int amount = slots[i].getMaxStackAmount() - slots[i].getAmount();
            slots[i].addAmount(amount);
            AbstractItem newItem = item.clone();
            newItem.addAmount(-amount);
            _item.setAmount(newItem.getAmount());
            return this.addItem(_item);
        }

        // van-e üres slot?
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                continue;
            }
            if (item.getAmount() <= item.getMaxStackAmount()) {
                slots[i] = item;
                _item.setAmount(0);
                return true;
            }
            int amount = item.getMaxStackAmount();
            AbstractItem newItem = item.clone();
            item.setAmount(amount);
            slots[i] = item;
            newItem.addAmount(-amount);
            _item.setAmount(newItem.getAmount());
            return this.addItem(_item);
        }

        return false;
    }

    @Override
    public AbstractItem dropItem(int index) {
        if (index < 0 || index >= slots.length) {
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
        if (index1 < 0 || index1 >= slots.length || index2 < 0 || index2 >= slots.length) {
            return false;
        }
        if (slots[index1] == null || slots[index2] == null) {
            return false;
        }
        AbstractItem temp = slots[index1];
        slots[index1] = slots[index2];
        slots[index2] = temp;
        return true;
    }

    @Override
    public boolean moveItem(int index, int newIndex) {
        if (index < 0 || index >= slots.length || newIndex < 0 || newIndex >= slots.length) {
            return false;
        }
        if (slots[index] == null) {
            return false;
        }
        if (slots[newIndex] != null) {
            return false;
        }
        slots[newIndex] = slots[index];
        slots[index] = null;
        return true;
    }

    @Override
    public boolean combineItems(int index1, int index2) {
        if (index1 == index2 || index1 < 0 || index1 >= slots.length || index2 < 0 || index2 >= slots.length) {
            return false;
        }
        if (slots[index1] == null || slots[index2] == null) {
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

        if (slots[index1].getAmount() + slots[index2].getAmount() 
            <= 
            slots[index1].getMaxStackAmount()) 
        {
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
        if (index < 0 || index >= slots.length) {
            return false;
        }
        if (slots[index] == null) {
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


    // TODO: Fix cookItem
    @Override
    public ItemType cookItem(int index) {
        if (index < 0 || index >= slots.length) {
            return null;
        }
        if (slots[index] == null) {
            return null;
        }
        if (!(slots[index] instanceof CookableItem)) {
            return null;
        }

        CookableItem item = (CookableItem)slots[index].clone();
        item.setAmount(1);
        slots[index].addAmount(-1);
        if (slots[index].getAmount() <= 0) {
            slots[index] = null;
        }

        addItem(item.cook());

        return item.getType();
    }

    @Override
    public ItemType eatItem(int index) {
        if (index < 0 || index >= slots.length) {
            return null;
        }
        if (slots[index] == null) {
            return null;
        }
        if (!(slots[index] instanceof EdibleItem)) {
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
    public int emptySlots() {
        int emptySlots_n = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                emptySlots_n++;
            }
        }
        return emptySlots_n;
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
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null || slots[i].getType() != type) {
                continue;
            }
            amount += slots[i].getAmount();
        }
        return amount;
    }
    
}