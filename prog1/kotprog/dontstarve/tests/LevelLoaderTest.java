package prog1.kotprog.dontstarve.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.Level;

public class LevelLoaderTest {

    @Test
    @DisplayName("loadLevel smoke test")
    public void loadLevelSmokeTest() {
        final GameManager gameManager = GameManager.getInstance();
        Level level = new Level(
            this.getClass().getResource("/level00.png").getPath()
        );
        gameManager.loadLevel(level);
        gameManager.loadLevel(level);

        Field field = (Field) gameManager.getField(0, 0);
        assertTrue(field.isWalkable());
        assertFalse(field.hasTree());
        assertFalse(field.hasStone());
        assertFalse(field.hasFire());
        assertFalse(field.hasBerry());
        assertFalse(field.hasCarrot());
        assertFalse(field.hasTwig());
        
    }
}
