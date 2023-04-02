package prog1.kotprog.dontstarve.tests;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.Character;
import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

public class CharacterTest {

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
    @DisplayName("Character property check, clamping")
    void characterPlain() {
        final Character character = new Character("player", new Position(0, 0));
        assertEquals(100f, character.getHp());
        assertEquals(100f, character.getHunger());

        character.setHp(110);
        character.setHunger(110);

        assertEquals(100f, character.getHp());
        assertEquals(110f, character.getHunger());
        character.tick();
        assertEquals(100f, character.getHunger());

        character.setHp(90);
        character.setHunger(90);

        assertEquals(90f, character.getHp());
        assertEquals(90f, character.getHunger());

        character.setHp(-10);
        character.setHunger(-10);

        assertEquals(0f, character.getHp());
        assertEquals(0f, character.getHunger());
    }
    
}
