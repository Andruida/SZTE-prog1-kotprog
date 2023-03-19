package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

public class Inventory implements BaseInventory {

    private AbstractItem[] slots;

    public Inventory() {
        slots = new AbstractItem[10];
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
                return true;
            }

            int amount = slots[i].getMaxStackAmount() - slots[i].getAmount();
            slots[i].addAmount(amount);
            AbstractItem newItem = item.clone();
            newItem.addAmount(-amount);
            return this.addItem(newItem);
        }

        // van-e üres slot?
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                continue;
            }
            if (item.getAmount() <= item.getMaxStackAmount()) {
                slots[i] = item;
                return true;
            }
            int amount = item.getMaxStackAmount();
            AbstractItem newItem = item.clone();
            item.setAmount(amount);
            slots[i] = item;
            newItem.addAmount(-amount);
            return this.addItem(newItem);
        }

        return false;
    }

    @Override
    public AbstractItem dropItem(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropItem'");
    }

    @Override
    public boolean removeItem(ItemType type, int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItem'");
    }

    @Override
    public boolean swapItems(int index1, int index2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swapItems'");
    }

    @Override
    public boolean moveItem(int index, int newIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveItem'");
    }

    @Override
    public boolean combineItems(int index1, int index2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'combineItems'");
    }

    @Override
    public boolean equipItem(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equipItem'");
    }

    @Override
    public EquippableItem unequipItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unequipItem'");
    }

    @Override
    public ItemType cookItem(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cookItem'");
    }

    @Override
    public ItemType eatItem(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eatItem'");
    }

    @Override
    public int emptySlots() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emptySlots'");
    }

    @Override
    public EquippableItem equippedItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equippedItem'");
    }

    @Override
    public AbstractItem getItem(int index) {
        return slots[index];
    }
    
}