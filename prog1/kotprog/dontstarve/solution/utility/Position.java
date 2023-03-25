package prog1.kotprog.dontstarve.solution.utility;

/**
 * Egy 2 dimenziós (x, y) koordinátát leíró osztály.
 */
public class Position {
    /**
     * vízszintes (x) irányú koordináta.
     */
    private float x;

    /**
     * függőleges (y) irányú koordináta.
     */
    private float y;

    /**
     * Két paraméteres konstruktor, amely segítségével egy új pozíciót lehet létrehozni.
     * @param x vízszintes (x) irányú koordináta
     * @param y függőleges (y) irányú koordináta
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Az aktuális koordinátához legközelebbi, csak egész értékű komponensekből álló koordináta kiszámítása.<br>
     * A kerekítés a matematika szabályainak megfelelően történik.
     * @return a kiszámolt pozíció
     */
    public Position getNearestWholePosition() {
        return new Position(Math.round(x), Math.round(y));
    }

    /**
     * Az aktuális pozícióhoz képest egy másik pozíció távolságának kiszámítása.
     * @param other a másik pozíció
     * @return a kiszámolt távolság
     */
    public float distanceTo(Position other) {
        return (float) Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * x koordináta gettere.
     * @return x koordináta
     */
    public float getX() {
        return x;
    }

    /**
     * y koordináta gettere.
     * @return y koordináta
     */
    public float getY() {
        return y;
    }

    /**
     * x koordináta settere.
     * @param x új x érték
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * y koordináta settere.
     * @param y új y érték
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Két pozíció egyenlőségét vizsgáló metódus.
     * @param o a másik pozíció
     * @return true, ha a két pozíció egyenlő, false, ha nem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Position position = (Position) o;

        if (Float.compare(position.x, x) != 0) {
            return false;
        }
        return Float.compare(position.y, y) == 0;
    }

}
