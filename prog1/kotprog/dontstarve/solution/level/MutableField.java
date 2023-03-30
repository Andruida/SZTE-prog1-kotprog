package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;

public interface MutableField extends BaseField {

    /**
     * Időegység eltelése.
     */
    void tick();

    /**
     * A mezőre rak egy tárgyat.
     * @param item a mezőre helyezni kívánt tárgy.
     */
    void addItem(AbstractItem item);

    /**
     * A mezőről felvesz egy tárgyat.
     * @return a mezőről felvehető tárgy. (null, ha nincs)
     */
    AbstractItem pickupItem();

    /**
     * Tűz settere.
     */
    void setFire(boolean state);

    /**
     * Kő settere.
     */
    void setStone(boolean state);

    /**
     * Fa settere.
     */
    void setTree(boolean state);

    /**
     * Bogyó settere.
     */
    void setBerry(boolean state);

    /**
     * Répa settere.
     */
    void setCarrot(boolean state);

    /**
     * Gally settere.
     */
    void setTwig(boolean state);

    /**
     * Interakcióba lépés a mezővel.
     * @param tool a használt eszköz.
     * @return az interakcióból származó tárgy. (null, ha több kör kell)
     */
    AbstractItem interact(EquippableItem tool);

}
