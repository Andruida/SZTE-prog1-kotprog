package prog1.kotprog.dontstarve.solution.utility;

/**
 * A játék állapotát leíró enum.
 */
public enum GameState {
    /**
     * A játék betöltése folyamatban van.
     */
    INIT,

    /**
     * A játék betöltése befejeződött, játékosok nem csatlakoztak.
     */
    LOADED,

    /**
     * A játék indítható.
     */
    READY,

    /**
     * A játék folyamatban van.
     */
    RUNNING,

    /**
     * A játék véget ért.
     */
    FINISHED
}
