package prog1.kotprog.dontstarve.tests;

import org.junit.jupiter.api.*;

import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.inventory.items.*;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class InventoryTest {

    private BaseInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        assumeNotNull(inventory);
    }


    @Test
    @DisplayName("Null hozzáadása nem okoz hibát")
    void addItemNullSafe() {
        assertFalse(inventory.addItem(null));
        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Üres item hozzáadása nem okoz hibát")
    void addItemEmpty() {
        AbstractItem item = new ItemLog(0);
        assertTrue(inventory.addItem(item));
        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Negatív item hozzáadása nem okoz hibát")
    void addItemNegativeAmount() {
        AbstractItem item = new ItemLog(-10);
        assertTrue(inventory.addItem(item));
        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadása üres slotra")
    void addItemEmptySlot() {
        AbstractItem item = new ItemLog(3);
        assertTrue(inventory.addItem(item));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(3, inventory.getItem(0).getAmount());
        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadása, stackelődés")
    void addItemStack() {
        AbstractItem item = new ItemLog(3);
        assertTrue(inventory.addItem(item));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(3, inventory.getItem(0).getAmount());
        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }

        AbstractItem item2 = new ItemLog(3);
        assertTrue(inventory.addItem(item2));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(6, inventory.getItem(0).getAmount());
        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Több item hozzáadása, mint amennyi a slotban elfér")
    void addItemMoreThanSlotCanHold() {
        AbstractItem item = new ItemLog(20);
        assertTrue(inventory.addItem(item));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(5, inventory.getItem(1).getAmount());
        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, többszörös túlcsordulás")
    void addItemMultipleOverflow() {
        AbstractItem item = new ItemLog(40);
        assertTrue(inventory.addItem(item));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(ItemType.LOG, inventory.getItem(2).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(15, inventory.getItem(1).getAmount());
        assertEquals(10, inventory.getItem(2).getAmount());
        for (int i = 3; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, többszörös túlcsordulás, több slotban")
    void addItemMultipleOverflowMultipleSlots() {
        AbstractItem item = new ItemLog(40);
        AbstractItem item2 = new ItemTwig(42);
        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(item2));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(ItemType.LOG, inventory.getItem(2).getType());
        assertEquals(ItemType.TWIG, inventory.getItem(3).getType());
        assertEquals(ItemType.TWIG, inventory.getItem(4).getType());
        assertEquals(ItemType.TWIG, inventory.getItem(5).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(15, inventory.getItem(1).getAmount());
        assertEquals(10, inventory.getItem(2).getAmount());
        assertEquals(20, inventory.getItem(3).getAmount());
        assertEquals(20, inventory.getItem(4).getAmount());
        assertEquals(2, inventory.getItem(5).getAmount());
        for (int i = 6; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, kézi eszköz")
    void addItemEquippable() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, kézi eszköz nem stackelhető")
    void addItemEquippableNonStackable() {
        AbstractItem tool = new ItemAxe();
        AbstractItem item = new ItemLog(2);
        AbstractItem tool2 = new ItemAxe();

        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.addItem(item));
        assertTrue(inventory.addItem(tool2));
        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(ItemType.AXE, inventory.getItem(2).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        assertEquals(2, inventory.getItem(1).getAmount());
        assertEquals(1, inventory.getItem(2).getAmount());
        for (int i = 3; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, object másolás")
    void addItemCopy() {
        AbstractItem item = new ItemLog(2);
        assertTrue(inventory.addItem(item));
        item.setAmount(3);
        assertEquals(2, inventory.getItem(0).getAmount());
    }

    @Test
    @DisplayName("Item hozzáadás, megmaradó nyersanyag")
    void addItemRemaining() {
        AbstractItem item = new ItemLog(10*15 + 7);
        assertFalse(inventory.addItem(item));

        for (int i = 0; i < 10; i++) {
            assertEquals(ItemType.LOG, inventory.getItem(0).getType());
            assertEquals(15, inventory.getItem(0).getAmount());
        }


        assertEquals(ItemType.LOG, item.getType());
        assertEquals(7, item.getAmount());

    }

    @Test
    @DisplayName("Item hozzáadás, tele inventory")
    void addItemFullInventory() {
        AbstractItem item = new ItemAxe();
        for (int i = 0; i < 10; i++) {
            assertTrue(inventory.addItem(item));
        }
        assertFalse(inventory.addItem(item));
    }

    @Test
    @DisplayName("Item hozzáadás, nem első slot")
    void addItemNotFirstSlot() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        AbstractItem item = new ItemLog(2);
        assertTrue(inventory.addItem(item));

        assertNotNull(inventory.dropItem(0));

        AbstractItem item2 = new ItemLog(2);
        assertTrue(inventory.addItem(item2));
        assertNull(inventory.getItem(0));
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(4, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, nem első slot, túlcsordulás")
    void addItemNotFirstSlotOverflow() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.addItem(tool));
        AbstractItem item = new ItemLog(2);
        assertTrue(inventory.addItem(item));

        assertNotNull(inventory.dropItem(0));
        assertNotNull(inventory.dropItem(1));
        assertNull(inventory.getItem(0));
        assertNull(inventory.getItem(1));

        AbstractItem item2 = new ItemLog(15);
        assertTrue(inventory.addItem(item2));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());
        assertNull(inventory.getItem(1));
        assertEquals(ItemType.LOG, inventory.getItem(2).getType());
        assertEquals(15, inventory.getItem(2).getAmount());

        for (int i = 3; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item hozzáadás, több megkezdett stack")
    void addItemMultipleOpenStacks() {
        AbstractItem item = new ItemLog(20);
        assertTrue(inventory.addItem(item));

        assertTrue(inventory.removeItem(ItemType.LOG, 10));

        AbstractItem item2 = new ItemLog(11);
        assertTrue(inventory.addItem(item2));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(6, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item eldobás, nem létező index")
    void dropItemInvalidIndex() {
        assertNull(inventory.dropItem(-1));
        assertNull(inventory.dropItem(10));
    }

    @Test
    @DisplayName("Item eldobás, üres inventory")
    void dropItemEmptyInventory() {
        assertNull(inventory.dropItem(0));
    }

    @Test
    @DisplayName("Item eldobás")
    void dropItem() {
        AbstractItem item = new ItemLog(2);
        assumeTrue(inventory.addItem(item));
        AbstractItem droppedItem = inventory.dropItem(0);
        assertNotNull(droppedItem);
        assertEquals(ItemType.LOG, droppedItem.getType());
        assertEquals(2, droppedItem.getAmount());

        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item eldobás, üres slot")
    void dropItemEmptySlot() {
        AbstractItem item = new ItemLog(2);
        assumeTrue(inventory.addItem(item));
        assertNull(inventory.dropItem(1));
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása")
    void removeItem() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.addItem(tool));
        AbstractItem item = new ItemLog(8);
        assertTrue(inventory.addItem(item));

        assertTrue(inventory.removeItem(ItemType.LOG, 3));
        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        assertEquals(ItemType.AXE, inventory.getItem(1).getType());
        assertEquals(1, inventory.getItem(1).getAmount());

        assertEquals(ItemType.LOG, inventory.getItem(2).getType());
        assertEquals(5, inventory.getItem(2).getAmount());

        for (int i = 3; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, üres inventory")
    void removeItemEmptyInventory() {
        assertFalse(inventory.removeItem(ItemType.LOG, 3));
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, pont elég")
    void removeItemEnough() {
        AbstractItem item = new ItemLog(8);
        assertTrue(inventory.addItem(item));

        assertTrue(inventory.removeItem(ItemType.LOG, 8));

        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, túl kevés")
    void removeItemNotEnough() {
        AbstractItem item = new ItemLog(8);
        assertTrue(inventory.addItem(item));

        assertFalse(inventory.removeItem(ItemType.LOG, 9));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(8, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, több stack")
    void removeItemMultipleStacks() {
        AbstractItem item = new ItemLog(50);
        assertTrue(inventory.addItem(item));

        assertTrue(inventory.removeItem(ItemType.LOG, 40));

        assertNull(inventory.getItem(0));
        assertNull(inventory.getItem(1));
        assertEquals(ItemType.LOG, inventory.getItem(2).getType());
        assertEquals(5, inventory.getItem(2).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(3).getType());
        assertEquals(5, inventory.getItem(3).getAmount());

        for (int i = 4; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, több stack, túl kevés")
    void removeItemMultipleStacksNotEnough() {
        AbstractItem item = new ItemLog(25);
        assertTrue(inventory.addItem(item));

        assertFalse(inventory.removeItem(ItemType.LOG, 26));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(10, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Adott típusú és mennyiségű item eltávolítása, több megkezdett stack")
    void removeItemMultipleOpenStacks() {
        AbstractItem item = new ItemLog(50);
        assertTrue(inventory.addItem(item));

        assertTrue(inventory.removeItem(ItemType.LOG, 40));

        assertTrue(inventory.removeItem(ItemType.LOG, 6));

        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                assertEquals(ItemType.LOG, inventory.getItem(i).getType());
                assertEquals(4, inventory.getItem(i).getAmount());
            } else {
                assertNull(inventory.getItem(i));
            }
        }
    }

    @Test
    @DisplayName("Itemek megcserélése")
    void swapItems() {
        AbstractItem item1 = new ItemLog(2);
        AbstractItem item2 = new ItemAxe();
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));

        assertTrue(inventory.swapItems(0, 1));

        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(2, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek megcserélése, üres slot")
    void swapItemsEmptySlot() {
        AbstractItem item1 = new ItemLog(2);
        assertTrue(inventory.addItem(item1));

        assertFalse(inventory.swapItems(0, 2));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek megcserélése, üres inventory")
    void swapItemsEmptyInventory() {
        assertFalse(inventory.swapItems(0, 2));
    }

    @Test
    @DisplayName("Itemek megcserélése, ugyanaz a slot")
    void swapItemsSameSlot() {
        AbstractItem item1 = new ItemLog(2);
        assertTrue(inventory.addItem(item1));

        assertTrue(inventory.swapItems(0, 0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek megcserélése, ugyanaz a slot, üres inventory")
    void swapItemsSameSlotEmptyInventory() {
        assertFalse(inventory.swapItems(0, 0));
    }

    @Test
    @DisplayName("Item mozgatása")
    void moveItem() {
        AbstractItem item1 = new ItemLog(2);
        assertTrue(inventory.addItem(item1));

        assertTrue(inventory.moveItem(0, 1));

        assertNull(inventory.getItem(0));
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(2, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item mozgatása, foglalt slot")
    void moveItemOccupiedSlot() {
        AbstractItem item1 = new ItemLog(2);
        AbstractItem item2 = new ItemAxe();
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));

        assertFalse(inventory.moveItem(0, 1));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());
        assertEquals(ItemType.AXE, inventory.getItem(1).getType());
        assertEquals(1, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item mozgatása, üres inventory")
    void moveItemEmptyInventory() {
        assertFalse(inventory.moveItem(0, 2));
    }

    @Test
    @DisplayName("Item mozgatása, ugyanaz a slot")
    void moveItemSameSlot() {
        AbstractItem item1 = new ItemLog(2);
        assertTrue(inventory.addItem(item1));

        assertFalse(inventory.moveItem(0, 0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(2, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item mozgatása, ugyanaz a slot, üres inventory")
    void moveItemSameSlotEmptyInventory() {
        assertFalse(inventory.moveItem(0, 0));
    }

    @Test
    @DisplayName("Empty slotok száma")
    void emptySlots() {
        assertEquals(10, inventory.emptySlots());

        AbstractItem item1 = new ItemLog(2);
        assertTrue(inventory.addItem(item1));

        assertEquals(9, inventory.emptySlots());

        AbstractItem item2 = new ItemAxe();
        assertTrue(inventory.addItem(item2));

        assertEquals(8, inventory.emptySlots());

        assertTrue(inventory.removeItem(ItemType.LOG, 1));
        assertEquals(8, inventory.emptySlots());


        assertTrue(inventory.removeItem(ItemType.LOG, 1));
        assertEquals(9, inventory.emptySlots());
    }

    @Test
    @DisplayName("Itemek kombinálása")
    void combineItems() {
        AbstractItem item1 = new ItemLog(20);
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.removeItem(ItemType.LOG, 10));

        assertTrue(inventory.combineItems(0, 1));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, nem egyező típus")
    void combineItemsDifferentType() {
        AbstractItem item1 = new ItemLog(10);
        AbstractItem item2 = new ItemAxe();
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));

        assertFalse(inventory.combineItems(0, 1));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());
        assertEquals(ItemType.AXE, inventory.getItem(1).getType());
        assertEquals(1, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, üres inventory")
    void combineItemsEmptyInventory() {
        assertFalse(inventory.combineItems(0, 1));
    }

    @Test
    @DisplayName("Itemek kombinálása, üres slot")
    void combineItemsEmptySlot() {
        AbstractItem item1 = new ItemLog(10);
        assertTrue(inventory.addItem(item1));

        assertFalse(inventory.combineItems(0, 1));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, ugyanaz a slot")
    void combineItemsSameSlot() {
        AbstractItem item1 = new ItemLog(10);
        assertTrue(inventory.addItem(item1));

        assertFalse(inventory.combineItems(0, 0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, érvénytelen index")
    void combineItemsInvalidIndex() {
        AbstractItem item1 = new ItemLog(20);
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.removeItem(ItemType.LOG, 10));

        assertFalse(inventory.combineItems(0, 10));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(5, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(5, inventory.getItem(0).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, már teli a slot")
    void combineItemsFullSlot() {
        AbstractItem item1 = new ItemLog(20);
        assertTrue(inventory.addItem(item1));

        assertFalse(inventory.combineItems(0, 1));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(15, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(5, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, teli a slot túlcsordulás")
    void combineItemsFullSlotOverflow() {
        AbstractItem item1 = new ItemLog(20);
        assertTrue(inventory.addItem(item1));

        assertTrue(inventory.combineItems(1, 0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(5, inventory.getItem(0).getAmount());
        assertEquals(ItemType.LOG, inventory.getItem(1).getType());
        assertEquals(15, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Itemek kombinálása, nem stackelhető")
    void combineItemsNotStackable() {
        AbstractItem item1 = new ItemAxe();
        AbstractItem item2 = new ItemAxe();
        assertTrue(inventory.addItem(item1));
        assertTrue(inventory.addItem(item2));

        assertFalse(inventory.combineItems(0, 1));

        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        assertEquals(ItemType.AXE, inventory.getItem(1).getType());
        assertEquals(1, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item kézbe vétele")
    void equipItem() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));

        assertTrue(inventory.equipItem(0));

        assertEquals(ItemType.AXE, inventory.equippedItem().getType());
        assertEquals(1, inventory.equippedItem().getAmount());
        assertNull(inventory.getItem(0));
    }

    @Test
    @DisplayName("Item kézbe vétele, üres inventory")
    void equipItemEmptyInventory() {
        assertFalse(inventory.equipItem(0));
    }

    @Test
    @DisplayName("Item kézbe vétele, nem kézbe vethető")
    void equipItemNotEquipable() {
        AbstractItem item = new ItemLog(10);
        assertTrue(inventory.addItem(item));

        assertFalse(inventory.equipItem(0));

        assertNull(inventory.equippedItem());
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());
    }

    @Test
    @DisplayName("Item kézbe vétele, érvénytelen index")
    void equipItemInvalidIndex() {
        AbstractItem item = new ItemLog(10);
        assertTrue(inventory.addItem(item));

        assertFalse(inventory.equipItem(10));

        assertNull(inventory.equippedItem());
        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());
    }

    @Test
    @DisplayName("Item kézbe vétele, már van a kézben")
    void equipItemAlreadyEquipped() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.equipItem(0));

        AbstractItem weapon = new ItemSpear();
        assertTrue(inventory.addItem(weapon));
        assertTrue(inventory.moveItem(0, 5));
        assertTrue(inventory.equipItem(5));

        assertEquals(ItemType.SPEAR, inventory.equippedItem().getType());
        assertEquals(1, inventory.equippedItem().getAmount());
        assertEquals(ItemType.AXE, inventory.getItem(5).getType());
        assertEquals(1, inventory.getItem(5).getAmount());

        for (int i = 0; i < 10; i++) {
            if (i == 5)
                continue;
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item kézből letevése")
    void unequipItem() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.equipItem(0));

        EquippableItem dequipped = inventory.unequipItem();
        assertNull(dequipped);

        assertNull(inventory.equippedItem());
        assertEquals(ItemType.AXE, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
    }

    @Test
    @DisplayName("Item kézből letevése, üres kéz")
    void unequipItemEmptyHand() {
        EquippableItem dequipped = inventory.unequipItem();
        assertNull(dequipped);

        assertNull(inventory.equippedItem());
    }

    @Test
    @DisplayName("Item kézből letevése, tele inventory")
    void unequipItemFullInventory() {
        AbstractItem tool = new ItemAxe();
        assertTrue(inventory.addItem(tool));
        assertTrue(inventory.equipItem(0));

        for (int i = 0; i < 10; i++) {
            assertTrue(inventory.addItem(new ItemLog(15)));
        }

        EquippableItem dequipped = inventory.unequipItem();
        assertNotNull(dequipped);
        assertEquals(ItemType.AXE, dequipped.getType());
        assertEquals(1, dequipped.getAmount());

        assertNull(inventory.equippedItem());
        
        for (int i = 0; i < 10; i++) {
            assertEquals(ItemType.LOG, inventory.getItem(i).getType());
            assertEquals(15, inventory.getItem(i).getAmount());
        }
    }

    @Test
    @DisplayName("Item sütése")
    void cookItem() {
        AbstractItem cookable = new ItemRawBerry(10);
        AbstractItem cookable1 = new ItemRawCarrot(10);

        assertTrue(inventory.addItem(cookable));
        assertTrue(inventory.addItem(cookable1));

        assertEquals(ItemType.RAW_BERRY,inventory.cookItem(0));
        assertEquals(ItemType.RAW_CARROT,inventory.cookItem(1));

        assertEquals(ItemType.RAW_BERRY, inventory.getItem(0).getType());
        assertEquals(9, inventory.getItem(0).getAmount());
        assertEquals(ItemType.RAW_CARROT, inventory.getItem(1).getType());
        assertEquals(9, inventory.getItem(1).getAmount());
        assertEquals(ItemType.COOKED_BERRY, inventory.getItem(2).getType());
        assertEquals(1, inventory.getItem(2).getAmount());
        assertEquals(ItemType.COOKED_CARROT, inventory.getItem(3).getType());
        assertEquals(1, inventory.getItem(3).getAmount());

        for (int i = 4; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item sütése, épp elég")
    void cookItemJustEnough() {
        AbstractItem cookable = new ItemRawBerry(1);
        AbstractItem cookable1 = new ItemRawCarrot(1);

        assertTrue(inventory.addItem(cookable));
        assertTrue(inventory.addItem(cookable1));

        assertEquals(ItemType.RAW_BERRY,inventory.cookItem(0));
        assertEquals(ItemType.RAW_CARROT,inventory.cookItem(1));

        assertEquals(ItemType.COOKED_BERRY, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());
        assertEquals(ItemType.COOKED_CARROT, inventory.getItem(1).getType());
        assertEquals(1, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item sütése, üres inventory")
    void cookItemEmptyInventory() {
        assertNull(inventory.cookItem(0));

        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item sütése, nem sütendő item")
    void cookItemNotCookable() {
        AbstractItem item = new ItemLog(10);
        assertTrue(inventory.addItem(item));

        assertNull(inventory.cookItem(0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item sütése, érvénytelen index")
    void cookItemInvalidIndex() {
        AbstractItem item = new ItemRawBerry(10);
        assertTrue(inventory.addItem(item));

        assertNull(inventory.cookItem(10));

        assertEquals(ItemType.RAW_BERRY, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item sütése, nincs elég hely")
    void cookItemNotEnoughSpace() {
        AbstractItem item = new ItemRawBerry(10);
        assertTrue(inventory.addItem(item));

        for (int i = 0; i < 9; i++) {
            assertTrue(inventory.addItem(new ItemLog(15)));
        }

        assertEquals(ItemType.RAW_BERRY, inventory.cookItem(0));

        assertEquals(ItemType.RAW_BERRY, inventory.getItem(0).getType());
        assertEquals(9, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertEquals(ItemType.LOG, inventory.getItem(i).getType());
            assertEquals(15, inventory.getItem(i).getAmount());
        }
    }

    @Test
    @DisplayName("Item sütése, nincs elég hely, de van elég hely a sütés után")
    void cookItemNotEnoughSpaceButEnoughAfterCooking() {
        AbstractItem item = new ItemRawBerry(1);
        assertTrue(inventory.addItem(item));

        for (int i = 1; i < 10; i++) {
            assertTrue(inventory.addItem(new ItemLog(15)));
        }

        assertEquals(ItemType.RAW_BERRY, inventory.cookItem(0));

        assertEquals(ItemType.COOKED_BERRY, inventory.getItem(0).getType());
        assertEquals(1, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertEquals(ItemType.LOG, inventory.getItem(i).getType());
            assertEquals(15, inventory.getItem(i).getAmount());
        }
    }

    @Test
    @DisplayName("Étel elfogyasztása")
    void eatItem() {
        AbstractItem edible = new ItemCookedBerry(10);
        AbstractItem edible2 = new ItemCookedCarrot(10);
        assertTrue(inventory.addItem(edible));
        assertTrue(inventory.addItem(edible2));

        assertEquals(ItemType.COOKED_BERRY, inventory.eatItem(0));
        assertEquals(ItemType.COOKED_CARROT, inventory.eatItem(1));

        assertEquals(ItemType.COOKED_BERRY, inventory.getItem(0).getType());
        assertEquals(9, inventory.getItem(0).getAmount());
        assertEquals(ItemType.COOKED_CARROT, inventory.getItem(1).getType());
        assertEquals(9, inventory.getItem(1).getAmount());

        for (int i = 2; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Étel elfogyasztása, épp elég")
    void eatItemJustEnough() {
        AbstractItem edible = new ItemCookedBerry(1);
        AbstractItem edible2 = new ItemCookedCarrot(1);
        assertTrue(inventory.addItem(edible));
        assertTrue(inventory.addItem(edible2));

        assertEquals(ItemType.COOKED_BERRY, inventory.eatItem(0));
        assertEquals(ItemType.COOKED_CARROT, inventory.eatItem(1));

        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Étel elfogyasztása, üres inventory")
    void eatItemEmptyInventory() {
        assertNull(inventory.eatItem(0));

        for (int i = 0; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Étel elfogyasztása, nem elfogyasztható item")
    void eatItemNotEdible() {
        AbstractItem item = new ItemLog(10);
        assertTrue(inventory.addItem(item));

        assertNull(inventory.eatItem(0));

        assertEquals(ItemType.LOG, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Étel elfogyasztása, érvénytelen index")
    void eatItemInvalidIndex() {
        AbstractItem item = new ItemCookedBerry(10);
        assertTrue(inventory.addItem(item));

        assertNull(inventory.eatItem(10));

        assertEquals(ItemType.COOKED_BERRY, inventory.getItem(0).getType());
        assertEquals(10, inventory.getItem(0).getAmount());

        for (int i = 1; i < 10; i++) {
            assertNull(inventory.getItem(i));
        }
    }

    @Test
    @DisplayName("Item lekérdezése, érvénytelen index")
    void getItemInvalidIndex() {
        assertNull(inventory.getItem(1000));
        assertNull(inventory.getItem(-10));
    }
}


