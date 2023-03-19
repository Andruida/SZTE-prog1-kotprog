package prog1.kotprog.dontstarve.tests;

import org.junit.jupiter.api.*;

import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.inventory.items.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private BaseInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        assertNotNull(inventory);
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
    @DisplayName("Item hozzáadás, tele inventory")
    void addItemFullInventory() {
        AbstractItem item = new ItemAxe();
        for (int i = 0; i < 10; i++) {
            assertTrue(inventory.addItem(item));
        }
        assertFalse(inventory.addItem(item));
    }

}


