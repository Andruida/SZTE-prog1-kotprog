package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.Inventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.MutableField;
import prog1.kotprog.dontstarve.solution.utility.Direction;
import prog1.kotprog.dontstarve.solution.utility.Position;


/**
 * Karakter osztály.
 */
public class Character implements MutableCharacter {

    /**
     * Karakter max életereje.
     */
    public static final float MAX_HP = 100f;

    /**
     * Karakter max éhsége.
     */
    public static final float MAX_HUNGER = 100f;

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
    public Character(String name, Position currentPosition, float hp, float hunger) {
        this.name = name;
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
        this(name, currentPosition, MAX_HP, MAX_HUNGER);
    }

    @Override
    public float getSpeed() {
        float hungerMultiplier = 1f;
        float hpMultiplier = 1f;
        if (hunger < 50 && hunger >= 20) {
            hungerMultiplier = 0.9f;
        }
        if (hunger < 20 && hunger > 0) {
            hungerMultiplier = 0.8f;
        }
        if (hunger <= 0) {
            hungerMultiplier = 0.5f;
        }

        if (hp < 50 && hp >= 30) {
            hpMultiplier = 0.9f;
        }
        if (hp < 30 && hp >= 10) {
            hpMultiplier = 0.75f;
        }
        if (hp < 10) {
            hpMultiplier = 0.6f;
        }

        return 1f * hungerMultiplier * hpMultiplier;
    }

    @Override
    public void tick() {
        addHunger(-0.4f);
        if (getHunger() <= 0) {
            addHp(-5f);
            setHunger(0f);
        }
        hunger = Math.min(hunger, MAX_HUNGER);
        inventory.tick();
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

    @Override
    public void setHp(float hp) {
        this.hp = Math.min(Math.max(hp, 0), MAX_HP);
        if (this.hp <= 0) {
            die();
        }
    }

    @Override
    public void addHp(float hp) {
        this.hp = Math.min(Math.max(this.hp + hp, 0), MAX_HP);
        if (this.hp <= 0) {
            die();
        }
    }

    @Override
    public void setHunger(float hunger) {
        this.hunger = Math.max(hunger, 0);
    }

    @Override
    public void addHunger(float hunger) {
        this.hunger = Math.max(this.hunger + hunger, 0);
    }

    @Override
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    @Override
    public void setPosition(float x, float y) {
        this.currentPosition = new Position(x, y);
    }

    @Override
    public void setLastAction(Action action) {
        this.lastAction = action;
    }

    @Override
    public void step(Direction direction) {
        Position pos = getCurrentPosition();
        float posX = pos.getX();
        float posY = pos.getY();
        float speed = getSpeed();
        switch (direction) {
            case UP:
                setPosition(posX, posY-speed);
                break;
            case DOWN:
                setPosition(posX, posY+speed);
                break;
            case LEFT:
                setPosition(posX-speed, posY);
                break;
            case RIGHT:
                setPosition(posX+speed, posY);
                break;
        }

        BaseField field = getCurrentPosition().getNearestField();
        if (field == null || !field.isWalkable()) {
            setPosition(pos);
        }
    }

    @Override
    public void attack() {
        Position pos = getCurrentPosition();
        MutableCharacter closestEnemy = null;
        for (BaseCharacter player : GameManager.getInstance().getCharacters()) {
            if (!(player instanceof MutableCharacter)) {
                continue;
            }
            if (player == this || player.getCurrentPosition().distanceTo(pos) > 2 || player.getHp() <= 0) {
                continue;
            }
            if (closestEnemy == null) {
                closestEnemy = (MutableCharacter) player;
                continue;
            }
            if (player.getCurrentPosition().distanceTo(pos) < closestEnemy.getCurrentPosition().distanceTo(pos)) {
                closestEnemy = (MutableCharacter) player;
            }
        }
        if (closestEnemy == null) {
            return;
        }

        if (inventory.equippedItem() == null) {
            closestEnemy.addHp(-4);
            return;
        }
        closestEnemy.addHp(-inventory.equippedItem().getDamage());
        if (inventory.equippedItem().getType() == ItemType.TORCH) {
            return;
        }
        inventory.equippedItem().wear();
        if (inventory.equippedItem().percentage() <= 0) {
            inventory.unequipItem();
        }

    }

    private void die() {
        MutableField field = (MutableField)currentPosition.getNearestField();

        field.addItem(inventory.equippedItem());
        for (int i = 0; i < 10; i++) {
            field.addItem(inventory.getItem(i));
        }
    }

}
