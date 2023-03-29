package prog1.kotprog.dontstarve.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
