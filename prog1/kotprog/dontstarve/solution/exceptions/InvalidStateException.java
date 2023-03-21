package prog1.kotprog.dontstarve.solution.exceptions;

/**
 * Kivétel osztály, amit a kód érvénytelen állapotba kerüléskor dob.
 */
public class InvalidStateException extends RuntimeException {
    /**
     * Default konstruktor, mellyel az objektum létrehozható.
     */
    public InvalidStateException() {
        super("Ennek nem lenne szabad elofordulnia!");
    }
}
