package prog1.kotprog.dontstarve.solution.character.actions;

// import prog1.kotprog.dontstarve.solution.character.MutableCharacter;

/**
 * Az aktuális mezőn lévő tereptárggyal való interakcióba lépés (favágás, kőcsákányozás, gally / bogyó / répa leszedése)
 * leírására szolgáló osztály.
 */
public class ActionInteract extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionInteract() {
        super(ActionType.INTERACT);
    }

    // @Override
    // public void execute(MutableCharacter character) {
    //     // TODO
    //     super.execute(character);
    // }
}
