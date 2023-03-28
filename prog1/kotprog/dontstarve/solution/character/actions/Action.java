package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;

/**
 * A karakterek egy akciójának leírására szolgáló osztály.
 */
public abstract class Action {
    /**
     * Az akció típusa.
     * @see ActionType
     */
    private final ActionType type;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     * @param type az akció típusa
     */
    public Action(ActionType type) {
        this.type = type;
    }

    /**
     * A type gettere.
     * @return az akció típusa
     */
    public ActionType getType() {
        return type;
    }

    /**
     * Az akció végrehajtása.
     * @param executor a végrehajtó karakter
     */
    public void execute(MutableCharacter executor) {
        executor.setLastAction(this);
    }
}
