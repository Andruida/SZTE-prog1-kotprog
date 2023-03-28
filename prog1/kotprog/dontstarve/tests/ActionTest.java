package prog1.kotprog.dontstarve.tests;

import org.junit.jupiter.api.*;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionDropItem;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.*;
import prog1.kotprog.dontstarve.solution.level.Level;
import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


public class ActionTest {

    private static final GameManager gameManager = GameManager.getInstance();

    private BaseInventory inventory;
    private BaseCharacter player;

    @BeforeEach
    void setUp() {
        setUpLevel("/level00.png");
        gameManager.joinCharacter("bot", false);
        gameManager.joinCharacter("player", true);

        player = gameManager.getCharacter("player");
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
}
