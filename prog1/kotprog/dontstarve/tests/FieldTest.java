package prog1.kotprog.dontstarve.tests;

import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemAxe;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemPickaxe;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.MapColors;

public class FieldTest {
    @Test
    @DisplayName("Field property check, plain")
    public void fieldPlain() {
        final Field field = new Field(MapColors.EMPTY);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());

        assertEquals(0, field.items().length);
    }

    @Test
    @DisplayName("Field property check, water")
    public void fieldWater() {
        final Field field = new Field(MapColors.WATER);
        assertFalse(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());

        assertEquals(0, field.items().length);
    }

    @Test
    @DisplayName("Field property check, tree")
    public void fieldTree() {
        final Field field = new Field(MapColors.TREE);
        assertTrue(field.isWalkable());
        assertTrue(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());

        assertEquals(0, field.items().length);
    }

    @Test
    @DisplayName("Field property check, stone")
    public void fieldStone() {
        final Field field = new Field(MapColors.STONE);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertTrue(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());

        assertEquals(0, field.items().length);
    }

    @Test
    @DisplayName("Field property check, twig")
    public void fieldTwig() {
        final Field field = new Field(MapColors.TWIG);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertTrue(field.hasTwig());
    }

    @Test
    @DisplayName("Field property check, berry")
    public void fieldBerry() {
        final Field field = new Field(MapColors.BERRY);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertTrue(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());

    }

    @Test
    @DisplayName("Field property check, carrot")
    public void fieldCarrot() {
        final Field field = new Field(MapColors.CARROT);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertTrue(field.hasCarrot());
        assertFalse(field.hasTwig());
    }

    @Test
    @DisplayName("Field twig collect with tool")
    public void fieldTwigCollectWithTool() {
        final Field field = new Field(MapColors.TWIG);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeFalse(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeTrue(field.hasTwig());
        
        EquippableItem tool = new ItemAxe();
        field.interact(tool);
        AbstractItem loot = field.interact(tool);
        assertEquals(100, tool.percentage());

        assertEquals(1, loot.getAmount());
        assertEquals(ItemType.TWIG, loot.getType());


        assertFalse(field.hasTwig());
    }

    @Test
    @DisplayName("Field twig collect without tool")
    public void fieldTwigCollectWithoutTool() {
        final Field field = new Field(MapColors.TWIG);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeFalse(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeTrue(field.hasTwig());
        
        assertNull(field.interact(null));
        AbstractItem loot = field.interact(null);
        assertEquals(1, loot.getAmount());
        assertEquals(ItemType.TWIG, loot.getType());

        assertFalse(field.hasTwig());
    }

    @Test
    @DisplayName("Field log collect with tool")
    public void fieldLogCollectWithTool() {
        final Field field = new Field(MapColors.TREE);
        assumeTrue(field.isWalkable());
        assumeTrue(field.hasTree());
        assumeFalse(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        EquippableItem tool = new ItemAxe();
        for (int i = 0; i < 3; i++) 
            assertNull(field.interact(tool));
        AbstractItem loot = field.interact(tool);
        assertEquals((float)36/40*100, tool.percentage());

        assertEquals(2, loot.getAmount());
        assertEquals(ItemType.LOG, loot.getType());

        assertFalse(field.hasTree());
    }

    @Test
    @DisplayName("Field log collect without tool")
    public void fieldLogCollectWithoutTool() {
        final Field field = new Field(MapColors.TREE);
        assumeTrue(field.isWalkable());
        assumeTrue(field.hasTree());
        assumeFalse(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        assertNull(field.interact(null));
        for (int i = 0; i < 3; i++) 
            assertNull(field.interact(null));
        assertNull(field.interact(null));

        assertTrue(field.hasTree());
    }

    @Test
    @DisplayName("Field log collect with wrong tool")
    public void fieldLogCollectWithWrongTool() {
        final Field field = new Field(MapColors.TREE);
        assumeTrue(field.isWalkable());
        assumeTrue(field.hasTree());
        assumeFalse(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        EquippableItem tool = new ItemPickaxe();
        for (int i = 0; i < 3; i++) 
            assertNull(field.interact(tool));
        assertNull(field.interact(tool));
        assertEquals(100, tool.percentage());

        assertTrue(field.hasTree());
    }

    @Test
    @DisplayName("Field stone collect with tool")
    public void fieldStoneCollectWithTool() {
        final Field field = new Field(MapColors.STONE);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeTrue(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        EquippableItem tool = new ItemPickaxe();
        for (int i = 0; i < 4; i++) 
            assertNull(field.interact(tool));
        AbstractItem loot = field.interact(tool);
        assertEquals((float)25/30*100, tool.percentage());

        assertEquals(3, loot.getAmount());
        assertEquals(ItemType.STONE, loot.getType());

        assertFalse(field.hasStone());
    }

    @Test
    @DisplayName("Field stone collect without tool")
    public void fieldStoneCollectWithoutTool() {
        final Field field = new Field(MapColors.STONE);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeTrue(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        assertNull(field.interact(null));
        for (int i = 0; i < 4; i++) 
            assertNull(field.interact(null));
        assertNull(field.interact(null));

        assertTrue(field.hasStone());
    }

    @Test
    @DisplayName("Field stone collect with wrong tool")
    public void fieldStoneCollectWithWrongTool() {
        final Field field = new Field(MapColors.STONE);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeTrue(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        EquippableItem tool = new ItemAxe();
        for (int i = 0; i < 4; i++) 
            assertNull(field.interact(tool));
        assertNull(field.interact(tool));
        assertEquals(100, tool.percentage());

        assertTrue(field.hasStone());
    }

    @Test
    @DisplayName("Field stone collect with damaged tool")
    public void fieldStoneCollectWithDamagedTool() {
        final Field field = new Field(MapColors.STONE);
        assumeTrue(field.isWalkable());
        assumeFalse(field.hasTree());
        assumeTrue(field.hasStone());
        assumeFalse(field.hasFire());
        assumeFalse(field.hasBerry());
        assumeFalse(field.hasCarrot());
        assumeFalse(field.hasTwig());
        
        EquippableItem tool = new ItemPickaxe();
        for (int i = 0; i < 26; i++) 
            tool.wear();
        for (int i = 0; i < 4; i++) 
            assertNull(field.interact(tool));
        assertNull(field.interact(tool));
        assertEquals(0, tool.percentage());

        assertTrue(field.hasStone());

        EquippableItem tool2 = new ItemPickaxe();
        AbstractItem loot = field.interact(tool2);
        assertEquals((float)29/30*100, tool2.percentage());

        assertEquals(3, loot.getAmount());
        assertEquals(ItemType.STONE, loot.getType());

        assertFalse(field.hasStone());

    }
}
