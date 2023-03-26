package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.utility.Direction;
import prog1.kotprog.dontstarve.solution.utility.Position;

/**
 * Egy egyszerű karakter leírására szolgáló interface.
 */
public interface MutableCharacter extends BaseCharacter {
    /**
     * Karakter életerejét állító metódus.
     * @param hp karakter életereje
     */
    void setHp(float hp);

    /**
     * Karakter életerejét növelő metódus.
     * @param hp hozzáadandó életerő (lehet negatív)
     */
    void addHp(float hp);

    /**
     * Karakter éhségét állító metódus.
     * @param hunger karakter éhsége
     */
    void setHunger(float hunger);

    /**
     * Karakter éhségét növelő metódus.
     * @param hunger hozzáadandó éhség (lehet negatív)
     */
    void addHunger(float hunger);

    /**
     * Karakter jelenlegi pozícióját állító metódus.
     * @param position karakter jelenlegi pozíciója
     */
    void setPosition(Position position);

    /**
     * Karakter jelenlegi pozícióját állító metódus.
     * @param x karakter jelenlegi x koordinátája
     * @param y karakter jelenlegi y koordinátája
     */
    void setPosition(float x, float y);

    /**
     * Karakter utolsó cselekvését állító metódus.
     * @param action karakter utolsó cselekvése
     */
    void setLastAction(Action action);

    /**
     * Egy időegység elteltével meghívott metódus.
     */
    void tick();

    /**
     * Karakter léptetése egy adott irányba.
     * @param direction a lépés iránya
     */
    void step(Direction direction);
}
