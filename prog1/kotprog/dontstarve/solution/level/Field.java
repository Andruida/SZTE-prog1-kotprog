package prog1.kotprog.dontstarve.solution.level;

import java.util.ArrayList;
import java.util.List;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;

public class Field implements MutableField {

    /**
     * A mezőn található-e víz.
     */
    private boolean water;

    /**
     * A mezőn található-e fa.
     */
    private boolean tree;

    /**
     * A mezőn található-e kő.
     */
    private boolean stone;

    /**
     * A mezőn található-e gally.
     */
    private boolean twig;

    /**
     * A mezőn található-e bogyó.
     */
    private boolean berry;

    /**
     * A mezőn található-e répa.
     */
    private boolean carrot;

    /**
     * A mezőn tűz élettartama.<br>
     * Ha 0, akkor nincs tűz.
     */
    private int fire;

    /**
     * A mezőn található itemek.
     */
    private List<AbstractItem> itemList;

    /**
     * Konstruktor.
     * @param color a mező színe a térképen
     */
    public Field(int color) {
        water = false;
        tree = false;
        stone = false;
        twig = false;
        berry = false;
        carrot = false;
        fire = 0;
        itemList = new ArrayList<>();


        switch (color) {
            case MapColors.WATER:
                water = true;
                break;
            case MapColors.TREE:
                tree = true;
                break;
            case MapColors.STONE:
                stone = true;
                break;
            case MapColors.TWIG:
                twig = true;
                break;
            case MapColors.BERRY:
                berry = true;
                break;
            case MapColors.CARROT:
                carrot = true;
                break;
            case MapColors.EMPTY:
            default:
                break;
        }
    }

    // /**
    //  * Metódus, ami megadja, hogy az adott típusú itemből van-e a mezőn.
    //  *
    //  * @param itemType az item, amiről lekérdezzük, hogy van-e a mezőn
    //  * @return true, ha van ilyen item a mezőn, false, ha nincs
    //  */
    // private boolean hasItem(ItemType itemType) {
    //     for (AbstractItem i : itemList) {
    //         if (i.getType() == itemType) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    @Override
    public boolean isEmpty() {
        return !water
            && !tree
            && !stone
            &&  fire <= 0
            && !berry
            && !carrot
            && !twig
            &&  itemList.isEmpty();
    }

    @Override
    public boolean isWalkable() {
        return !water;
    }

    @Override
    public boolean hasTree() {
        return tree;
    }

    @Override
    public boolean hasStone() {
        return stone;
    }

    @Override
    public boolean hasTwig() {
        return twig;
    }

    @Override
    public boolean hasBerry() {
        return berry;
    }

    @Override
    public boolean hasCarrot() {
        return carrot;
    }

    @Override
    public boolean hasFire() {
        return fire > 0;
    }

    @Override
    public AbstractItem[] items() {
        return itemList.toArray(new AbstractItem[itemList.size()]);
    }

    @Override
    public void addItem(AbstractItem item) {
        if (item == null) {
            return;
        }
        itemList.add(item);
    }

    @Override
    public AbstractItem pickupItem() {
        if (itemList.isEmpty()) {
            return null;
        }
        return itemList.remove(0);
    }

    @Override
    public void setFire(boolean state) {
        fire = state ? 60 : 0;
    }

    @Override
    public void setStone(boolean state) {
        stone = state;
    }

    @Override
    public void setTree(boolean state) {
        tree = state;
    }

    @Override
    public void setTwig(boolean state) {
        twig = state;
    }

    @Override
    public void setBerry(boolean state) {
        berry = state;
    }

    @Override
    public void setCarrot(boolean state) {
        carrot = state;
    }

    @Override
    public void tick() {
        if (fire > 0) {
            fire--;
        }
    }

}
