package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

public class Inventory implements BaseInventory {

    private AbstractItem[] slots = new AbstractItem[10];

    public Inventory() {
        
    }

    @Override
    public boolean addItem(AbstractItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropItem'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItem'");
    }
    
}