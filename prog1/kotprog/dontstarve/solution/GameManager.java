package prog1.kotprog.dontstarve.solution;

import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.Character;
import prog1.kotprog.dontstarve.solution.character.MutableCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.exceptions.NotImplementedException;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemLog;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemStone;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.MutableField;
import prog1.kotprog.dontstarve.solution.level.Field;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.GameState;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A játék működéséért felelős osztály.<br>
 * Az osztály a singleton tervezési mintát valósítja meg.
 */
public final class GameManager {
    /**
     * Az osztályból létrehozott egyetlen példány (nem lehet final).
     */
    private static GameManager instance = new GameManager();

    /**
     * Random objektum, amit a játék során használni lehet.
     */
    private final Random random = new Random();

    /**
     * A játékban lévő karakterek.
     */
    private Map<String, BaseCharacter> characters;

    /**
     * A pálya.
     */
    private BaseField[][] level;

    /**
     * A játékban eltelt időegységek száma.
     */
    private int currentTick;

    /**
     * A játék állapota.
     */
    private GameState gameState;

    /**
     * A játék tutorial módban induljon-e.
     */
    private boolean tutorial;

    /**
     * Emberi játékos neve.
     */
    private String humanPlayerName;

    /**
     * Az osztály privát konstruktora.
     */
    private GameManager() {
        reset();
    }

    /**
     * Visszaállítja a GameManager-t a kiinduló állapotba.
     * Teszteléskor hasznos.
     */
    public void reset() {
        level = null;
        tutorial = false;
        gameState = GameState.INIT;
        currentTick = 0;
        characters = new HashMap<>();
        humanPlayerName = null;
    }

    /**
     * Az osztályból létrehozott példány elérésére szolgáló metódus.
     * @return az osztályból létrehozott példány
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * A létrehozott random objektum elérésére szolgáló metódus.
     * @return a létrehozott random objektum
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Egy karakter becsatlakozása a játékba.<br>
     * A becsatlakozásnak számos feltétele van:
     * <ul>
     *     <li>A pálya már be lett töltve</li>
     *     <li>A játék még nem kezdődött el</li>
     *     <li>Csak egyetlen emberi játékos lehet, a többi karaktert a gép irányítja</li>
     *     <li>A névnek egyedinek kell lennie</li>
     * </ul>
     * @param name a csatlakozni kívánt karakter neve
     * @param player igaz, ha emberi játékosról van szó; hamis egyébként
     * @return a karakter pozíciója a pályán, vagy (Integer.MAX_VALUE, Integer.MAX_VALUE) ha nem sikerült hozzáadni
     */
    public Position joinCharacter(String name, boolean player) {
        if (gameState != GameState.LOADED && gameState != GameState.READY) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        if (characters.containsKey(name)) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        if (player && humanPlayerName != null) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        Position position = generatePosition();
        if (position.equals(new Position(Integer.MAX_VALUE, Integer.MAX_VALUE))) {
            return position;
        }

        if (player) {
            humanPlayerName = name;
        }
        if (gameState == GameState.LOADED && humanPlayerName != null && !characters.isEmpty()) {
            gameState = GameState.READY;
        }

        BaseCharacter character = new Character(name, position);
        characters.put(name, character);
        initInventory(character);
        return position;
    }

    /**
     * Játékos inventoryjának inicializálása.
     * @param character a karakter, aminek az inventoryját inicializáljuk
     */
    public void initInventory(BaseCharacter character) {
        BaseInventory inventory = character.getInventory();
        for (int i = 0; i < 4; i++) {
            switch (random.nextInt(5)) {
                case 0:
                    inventory.addItem(new ItemLog(1));
                    break;
                case 1:
                    inventory.addItem(new ItemStone(1));
                    break;
                case 2:
                    inventory.addItem(new ItemTwig(1));
                    break;
                case 3:
                    inventory.addItem(new ItemRawBerry(1));
                    break;
                case 4:
                    inventory.addItem(new ItemRawCarrot(1));
                    break;
            }
        }
    }

    /**
     * Egy csatlakozó játékos elhelyezése.
     * @return a karakter pozíciója a pályán, vagy (Integer.MAX_VALUE, Integer.MAX_VALUE) ha nem sikerült hozzáadni
     */
    public Position generatePosition() {
        if (level == null || characters == null || random == null) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        boolean[][] possibleLocations = new boolean[level.length][level[0].length];
        int found = 0;

        for (int d = 50; d > 0; d -= 5) {
            possibleLocations = new boolean[level.length][level[0].length];
            found = 0;

            for (int i = 0; i < level.length; i++) {
                for (int j = 0; j < level[0].length; j++) {
                    possibleLocations[i][j] = ((Field)level[i][j]).isEmpty();
                    if (possibleLocations[i][j]) {
                        found++;
                    }
                }
            }

            for (BaseCharacter character : characters.values()) {
                Position position = character.getCurrentPosition().getNearestWholePosition();
                int posX = (int) position.getX();
                int posY = (int) position.getY();


                for (int i = posX - d; i <= posX + d; i++) {
                    if (i < 0 || i >= level.length) {
                        continue;
                    }
                    for (int j = posY - d; j <= posY + d; j++) {
                        if (j < 0 || j >= level[0].length) {
                            continue;
                        }
                        if (possibleLocations[i][j] && position.distanceTo(new Position(i, j)) < d) {
                            possibleLocations[i][j] = false;
                            found--;
                        }
                    }
                }
            }

            if (found > 0) {
                break;
            }
        }

        if (found <= 0) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        int index = random.nextInt(found);
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                if (possibleLocations[i][j]) {
                    if (index == 0) {
                        return new Position(i, j);
                    }
                    index--;
                }
            }
        }

        return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Egy adott nevű karakter lekérésére szolgáló metódus.<br>
     * @param name A lekérdezni kívánt karakter neve
     * @return Az adott nevű karakter objektum, vagy null, ha már a karakter meghalt vagy nem is létezett
     */
    public BaseCharacter getCharacter(String name) {
        return characters.get(name);
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy hány karakter van még életben.
     * @return Az életben lévő karakterek száma
     */
    public int remainingCharacters() {
        int count = 0;
        for (BaseCharacter character : characters.values()) {
            if (character.getHp() > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Ezen metódus segítségével történhet meg a pálya betöltése.<br>
     * A pálya betöltésének azelőtt kell megtörténnie, hogy akár 1 karakter is csatlakozott volna a játékhoz.<br>
     * A pálya egyetlen alkalommal tölthető be, később nem módosítható.
     * @param level a fájlból betöltött pálya
     */
    public void loadLevel(Level level) {
        if (this.level != null) {
            return;
        }

        this.level = new BaseField[level.getWidth()][level.getHeight()];
        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                this.level[x][y] = new Field(level.getColor(x, y));
            }
        }

        gameState = GameState.LOADED;
    }

    /**
     * A pálya egy adott pozícióján lévő mező lekérdezésére szolgáló metódus.
     * @param x a vízszintes (x) irányú koordináta
     * @param y a függőleges (y) irányú koordináta
     * @return az adott koordinátán lévő mező
     */
    public BaseField getField(int x, int y) {
        if (x < 0 || x >= level.length || y < 0 || y >= level[0].length) {
            return null;
        }
        return level[x][y];
    }

    /**
     * A játék megkezdésére szolgáló metódus.<br>
     * A játék csak akkor kezdhető el, ha legalább 2 karakter már a pályán van,
     * és közülük pontosan az egyik az emberi játékos által irányított karakter.
     * @return igaz, ha sikerült elkezdeni a játékot; hamis egyébként
     */
    public boolean startGame() {
        if (gameState == GameState.READY) {
            gameState = GameState.RUNNING;
            return true;
        }
        return false;
    }

    /**
     * Ez a metódus jelzi, hogy 1 időegység eltelt.<br>
     * A metódus először lekezeli a felhasználói inputot, majd a gépi ellenfelek cselekvését végzi el,
     * végül eltelik egy időegység.<br>
     * Csak akkor csinál bármit is, ha a játék már elkezdődött, de még nem fejeződött be.
     * @param action az emberi játékos által végrehajtani kívánt akció
     */
    public void tick(Action action) {
        if (gameState != GameState.RUNNING) {
            return;
        }
        if (action == null) {
            return;
        }

        MutableCharacter humanPlayer = (MutableCharacter) characters.get(humanPlayerName);
        action.execute(humanPlayer);

        for (BaseCharacter character : characters.values()) {
            if (character.getHp() <= 0) {
                continue;
            }
            if (!(character instanceof MutableCharacter)) {
                continue;
            }
            ((MutableCharacter)character).tick();
        }

        for (BaseField[] row : level) {
            for (BaseField field : row) {
                ((MutableField)field).tick();
            }
        }
        currentTick++;
    }

    /**
     * Ezen metódus segítségével lekérdezhető az aktuális időpillanat.<br>
     * A játék kezdetekor ez az érték 0 (tehát a legelső időpillanatban az idő 0),
     * majd minden eltelt időpillanat után 1-gyel növelődik.
     * @return az aktuális időpillanat
     */
    public int time() {
        return currentTick;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük a játék győztesét.<br>
     * Amennyiben a játéknak még nincs vége (vagy esetleg nincs győztes), akkor null-t ad vissza.
     * @return a győztes karakter vagy null
     */
    public BaseCharacter getWinner() {
        if (gameState != GameState.FINISHED) {
            return null;
        }
        throw new NotImplementedException();
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék elkezdődött-e már.
     * @return igaz, ha a játék már elkezdődött; hamis egyébként
     */
    public boolean isGameStarted() {
        return gameState == GameState.RUNNING;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék befejeződött-e már.
     * @return igaz, ha a játék már befejeződött; hamis egyébként
     */
    public boolean isGameEnded() {
        return gameState == GameState.FINISHED;
    }

    /**
     * Ezen metódus segítségével beállítható, hogy a játékot tutorial módban szeretnénk-e elindítani.<br>
     * Alapértelmezetten (ha nem mondunk semmit) nem tutorial módban indul el a játék.<br>
     * Tutorial módban a gépi karakterek nem végeznek cselekvést, csak egy helyben állnak.<br>
     * A tutorial mód beállítása még a karakterek csatlakozása előtt történik.
     * @param tutorial igaz, amennyiben tutorial módot szeretnénk; hamis egyébként
     */
    public void setTutorial(boolean tutorial) {
        if (gameState != GameState.LOADED) {
            return;
        }
        this.tutorial = tutorial;
    }
}
