package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.utility.Position;


/**
 * Karakter osztály.
 */
public class Character implements BaseCharacter {

    /**
     * Karakter sebessége.
     */
    private float speed;

    /**
     * Karakter éhsége.
     */
    private float hunger;

    /**
     * Karakter életereje.
     */
    private float hp;

    /**
     * Karakter inventory-ja.
     */
    private final BaseInventory inventory = new Inventory();

    /**
     * Karakter jelen pozíciója.
     */
    private Position currentPosition;

    /**
     * Karakter utolsó cselekvése.
     */
    private Action lastAction = new ActionNone();

    /**
     * Karakter neve.
     */
    private String name;

    /**
     * Karakter konstruktora.
     * @param name karakter neve
     * @param speed karakter sebessége
     * @param hunger karakter éhsége
     * @param hp karakter életereje
     * @param currentPosition karakter jelenlegi pozíciója
     */
    public Character(String name, Position currentPosition, float speed, float hunger, float hp) {
        this.name = name;
        this.speed = speed;
        this.hunger = hunger;
        this.hp = hp;
        this.currentPosition = currentPosition;
    }

    /**
     * Karakter konstruktora.
     * @param name karakter neve
     * @param currentPosition karakter jelenlegi pozíciója
     */
    public Character(String name, Position currentPosition) {
        this(name, currentPosition, 1, 1, 1);
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getHunger() {
        return hunger;
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public BaseInventory getInventory() {
        return inventory;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public Action getLastAction() {
        return lastAction;
    }

    @Override
    public String getName() {
        return name;
    }


}
