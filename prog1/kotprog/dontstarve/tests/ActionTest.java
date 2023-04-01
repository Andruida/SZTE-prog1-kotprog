package prog1.kotprog.dontstarve.tests;

import org.junit.jupiter.api.*;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionDropItem;
import prog1.kotprog.dontstarve.solution.character.actions.ActionEquip;
import prog1.kotprog.dontstarve.solution.character.actions.ActionInteract;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.*;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


public class ActionTest {

    private static final GameManager gameManager = GameManager.getInstance();

    private BaseInventory inventory;
    private MutableCharacter player;

    @BeforeEach
    void setUp() {
        setUpLevel("/level00.png");
        gameManager.joinCharacter("bot", false);
        gameManager.joinCharacter("player", true);

        player = (MutableCharacter)gameManager.getCharacter("player");
        assumeNotNull(player);
        assumeTrue(gameManager.startGame());

        inventory = player.getInventory();
    }

    void setUpLevel(String levelName) {
        gameManager.reset();
        gameManager.getRandom().setSeed(1);
        Level level = new Level(
            this.getClass().getResource(levelName).getPath()
        );
        assumeNotNull(level);
        gameManager.loadLevel(level);
    }

    @Test
    @DisplayName("Item eldobása")
    void testActionDropItem() {
        inventory.dropItem(0);
        inventory.addItem(new ItemLog(10));

        Action action = new ActionDropItem(0);
        gameManager.tick(action);

        assertEquals(1, player.getCurrentPosition().getNearestField().items().length);
        assertEquals(ItemType.LOG, player.getCurrentPosition().getNearestField().items()[0].getType());
        assertEquals(10, player.getCurrentPosition().getNearestField().items()[0].getAmount());
    }

    @Test
    @DisplayName("Item eldobása, ha nincs ott")
    void testActionDropItemNoItem() {
        
        inventory.dropItem(0);

        Action action = new ActionDropItem(0);
        gameManager.tick(action);

        assertEquals(0, player.getCurrentPosition().getNearestField().items().length);
    }

    @Test
    @DisplayName("Favágás eszköz nélkül")
    void testActionChopTreeWithoutTool() {
        player.setPosition(3, 1);
        assumeTrue(player.getCurrentPosition().getNearestField().hasTree());

        for (int i = 0; i < 5; i++) {
            Action action = new ActionInteract();
            gameManager.tick(action);
        }

        assertTrue(player.getCurrentPosition().getNearestField().hasTree());
    }

    @Test
    @DisplayName("Favágás")
    void testActionChopTree() {
        player.setPosition(3, 1);
        assumeTrue(player.getCurrentPosition().getNearestField().hasTree());
        inventory.dropItem(0);
        inventory.addItem(new ItemAxe());
        assumeTrue(inventory.getItem(0).getType() == ItemType.AXE);

        Action equipAction = new ActionEquip(0);
        gameManager.tick(equipAction);

        for (int i = 0; i < 4; i++) {
            Action action = new ActionInteract();
            gameManager.tick(action);
        }

        assertFalse(player.getCurrentPosition().getNearestField().hasTree());
        assertNull(inventory.getItem(0));

        assertEquals(1, player.getCurrentPosition().getNearestField().items().length);
        assertEquals(ItemType.LOG, player.getCurrentPosition().getNearestField().items()[0].getType());
        assertEquals(2, player.getCurrentPosition().getNearestField().items()[0].getAmount());

        assertEquals((float)36/40*100, inventory.equippedItem().percentage());

    }

    @Test
    @DisplayName("Favágás, éppen elkopó eszköz")
    void testActionChopTreeWithBreakingTool() {
        player.setPosition(3, 1);
        assumeTrue(player.getCurrentPosition().getNearestField().hasTree());
        inventory.dropItem(0);
        inventory.addItem(new ItemAxe());
        assumeTrue(inventory.getItem(0).getType() == ItemType.AXE);

        for (int i = 0; i < 36; i++) {
            ((EquippableItem)inventory.getItem(0)).wear();
        }

        Action equipAction = new ActionEquip(0);
        gameManager.tick(equipAction);

        for (int i = 0; i < 4; i++) {
            Action action = new ActionInteract();
            gameManager.tick(action);
        }

        assertFalse(player.getCurrentPosition().getNearestField().hasTree());
        assertNull(inventory.getItem(0));

        assertEquals(1, player.getCurrentPosition().getNearestField().items().length);
        assertEquals(ItemType.LOG, player.getCurrentPosition().getNearestField().items()[0].getType());
        assertEquals(2, player.getCurrentPosition().getNearestField().items()[0].getAmount());

        assertNull(inventory.equippedItem());

    }
}
