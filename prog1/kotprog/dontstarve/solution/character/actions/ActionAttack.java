package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.character.MutableCharacter;

/**
 * A támadás akció leírására szolgáló osztály: a legközelebbi karakter megtámadása.
 */
public class ActionAttack extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionAttack() {
        super(ActionType.ATTACK);
    }

    @Override
    public void execute(MutableCharacter executor) {
        executor.attack();
        super.execute(executor);
    }
}
