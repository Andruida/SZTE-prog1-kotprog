package prog1.kotprog.dontstarve.tests;

import org.junit.jupiter.api.*;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.*;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

class JoinCharacterTest {

    private static final GameManager gameManager = GameManager.getInstance();
    private static final Position failed = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);

    @BeforeEach
    void setUp() {
        setUpLevel("/level00.png");
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
    @DisplayName("Játékos csatlakozása pálya betöltése előtt")
    void joinBeforeLoad() {
        gameManager.reset();

        assertTrue(gameManager.joinCharacter("player", false).equals(failed));
        assertEquals(0, gameManager.remainingCharacters());

    }

    @Test
    @DisplayName("1 játékos csatlakozása")
    void joinCharacter() {
        Position position = gameManager.joinCharacter("player", false);
        assertNotNull(position);
        assertFalse(position.equals(failed));
        int x = (int)position.getX();
        int y = (int)position.getY();

        assertTrue(((Field)gameManager.getField(x, y)).isEmpty());

        BaseInventory inventory = gameManager.getCharacter("player").getInventory();
        int amount = 0;
        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) != null) {
                amount += inventory.getItem(i).getAmount();
            }
        }
        assertEquals(4, amount);

        assertEquals(1, gameManager.remainingCharacters());

    }

    @Test
    @DisplayName("2 játékos csatlakozása ugyanazzal a névvel")
    void joinTwoCharactersWithSameName() {
        Position position1 = gameManager.joinCharacter("player", false);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));

        Position position2 = gameManager.joinCharacter("player", false);
        assertNotNull(position2);
        assertTrue(position2.equals(failed));

        assertEquals(1, gameManager.remainingCharacters());
    }

    @Test
    @DisplayName("2 játékos csatlakozása")
    void joinTwoCharacters() {
        Position position1 = gameManager.joinCharacter("player1", false);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));
        int x1 = (int)position1.getX();
        int y1 = (int)position1.getY();

        Position position2 = gameManager.joinCharacter("player2", false);
        assertNotNull(position2);
        assertFalse(position2.equals(failed));
        int x2 = (int)position2.getX();
        int y2 = (int)position2.getY();

        assertTrue(((Field)gameManager.getField(x1, y1)).isEmpty());
        assertTrue(((Field)gameManager.getField(x2, y2)).isEmpty());
        assertTrue(position1.distanceTo(position2) >= 50);

        assertEquals(2, gameManager.remainingCharacters());

    }

    @Test
    @DisplayName("1 emberi játékos csatlakozása")
    void joinHumanCharacter() {
        Position position = gameManager.joinCharacter("player", true);
        assertNotNull(position);
        assertFalse(position.equals(failed));
        int x = (int)position.getX();
        int y = (int)position.getY();

        assertTrue(((Field)gameManager.getField(x, y)).isEmpty());

        assertFalse(gameManager.startGame());
        assertEquals(1, gameManager.remainingCharacters());

    }

    @Test
    @DisplayName("1 emberi játékos csatlakozása, 1 gépi játékos csatlakozása")
    void joinHumanAndComputerCharacter() {
        Position position1 = gameManager.joinCharacter("player", true);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));
        int x1 = (int)position1.getX();
        int y1 = (int)position1.getY();

        Position position2 = gameManager.joinCharacter("player2", false);
        assertNotNull(position2);
        assertFalse(position2.equals(failed));
        int x2 = (int)position2.getX();
        int y2 = (int)position2.getY();

        assertTrue(((Field)gameManager.getField(x1, y1)).isEmpty());
        assertTrue(((Field)gameManager.getField(x2, y2)).isEmpty());
        assertTrue(position1.distanceTo(position2) >= 50);

        assertTrue(gameManager.startGame());
        assertEquals(2, gameManager.remainingCharacters());
    }

    @Test
    @DisplayName("2 emberi játékos csatlakozása")
    void joinTwoHumanCharacters() {
        Position position1 = gameManager.joinCharacter("player1", true);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));

        Position position2 = gameManager.joinCharacter("player2", true);
        assertNotNull(position2);
        assertTrue(position2.equals(failed));

        assertEquals(1, gameManager.remainingCharacters());
        assertFalse(gameManager.startGame());
    }

    @Test
    @DisplayName("sok gépi játékos csatlakozása")
    void joinManyComputerCharacters() {
        for (int i = 0; i < 200; i++) {
            Position position = gameManager.joinCharacter("player" + i, false);
            assertNotNull(position);
            assertFalse(position.equals(failed));
            int x = (int)position.getX();
            int y = (int)position.getY();

            assertTrue(((Field)gameManager.getField(x, y)).isEmpty());
        }

        assertFalse(gameManager.startGame());
        assertEquals(200, gameManager.remainingCharacters());
    }

    @Test
    @DisplayName("2 játékos csatlakozása (nagyon kicsi pálya)")
    void joinTwoCharactersExtremelySmallLevel() {
        setUpLevel("/level01.png");

        Position position1 = gameManager.joinCharacter("player1", false);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));

        Position position2 = gameManager.joinCharacter("player2", false);
        assertNotNull(position2);
        assertTrue(position2.equals(failed));

        assertEquals(1, gameManager.remainingCharacters());
    }

    @Test
    @DisplayName("pár játékos csatlakozása (kicsi pálya)")
    void joinTwoCharactersSmallLevel() {
        setUpLevel("/level02.png");

        for (int i = 0; i < 4; i++) {
            Position position = gameManager.joinCharacter("player" + i, false);
            assertNotNull(position);
            assertFalse(position.equals(failed));
            int x = (int)position.getX();
            int y = (int)position.getY();

            assertTrue(((Field)gameManager.getField(x, y)).isEmpty());
        }
        
        Position positionN = gameManager.joinCharacter("playerN", false);
        assertNotNull(positionN);
        assertTrue(positionN.equals(failed));

        assertEquals(4, gameManager.remainingCharacters());

    }

    @Test
    @DisplayName("játékos csatlakozása indítás után")
    void joinAfterStart() {

        assertFalse(gameManager.startGame());

        Position position = gameManager.joinCharacter("player", false);
        assertNotNull(position);
        assertFalse(position.equals(failed));

        assertFalse(gameManager.startGame());

        Position position1 = gameManager.joinCharacter("player2", true);
        assertNotNull(position1);
        assertFalse(position1.equals(failed));

        assertTrue(gameManager.startGame());

        Position position2 = gameManager.joinCharacter("player3", false);
        assertNotNull(position2);
        assertTrue(position2.equals(failed));

        assertFalse(gameManager.startGame());    
        assertEquals(2, gameManager.remainingCharacters());    
    }

    
}
