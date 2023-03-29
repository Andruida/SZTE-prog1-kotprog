package prog1.kotprog.dontstarve.solution.level;

import java.util.ArrayList;
import java.util.List;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemLog;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemStone;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;

public class Field implements MutableField {

    /**
     * A mezőn található-e víz.
     */
    private boolean water;

    /**
     * A mezőn található-e fa.
     */
    private int tree;

    /** 
     * Fa kivágásának ideje.
     */
    private static final int TREE_DURATION = 4;

    /**
     * A mezőn található-e kő.
     */
    private int stone;

    /** 
     * Kő kifejtésének ideje.
     */
    private static final int STONE_DURATION = 5;

    /**
     * A mezőn található-e gally.
     */
    private int twig;

    /** 
     * Gally leszedésének ideje.
     */
    private static final int TWIG_DURATION = 2;

    /**
     * A mezőn található-e bogyó.
     */
    private int berry;

    /** 
     * Bogyó leszedésének ideje.
     */
    private static final int BERRY_DURATION = 1;

    /**
     * A mezőn található-e répa.
     */
    private int carrot;

    /** 
     * Répa begyűjtésének ideje.
     */
    private static final int CARROT_DURATION = 1;

    /**
     * A mezőn tűz élettartama.<br>
     * Ha 0, akkor nincs tűz.
     */
    private int fire;

    /** 
     * Tűz élettartama.
     */
    private static final int FIRE_DURATION = 60;

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
        tree = 0;
        stone = 0;
        twig = 0;
        berry = 0;
        carrot = 0;
        fire = 0;
        itemList = new ArrayList<>();


        switch (color) {
            case MapColors.WATER:
                water = true;
                break;
            case MapColors.TREE:
                tree = TREE_DURATION;
                break;
            case MapColors.STONE:
                stone = STONE_DURATION;
                break;
            case MapColors.TWIG:
                twig = TWIG_DURATION;
                break;
            case MapColors.BERRY:
                berry = BERRY_DURATION;
                break;
            case MapColors.CARROT:
                carrot = CARROT_DURATION;
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
            &&  tree <= 0
            &&  stone <= 0
            &&  fire <= 0
            &&  berry <= 0
            &&  carrot <= 0
            &&  twig <= 0
            &&  itemList.isEmpty();
    }

    @Override
    public boolean isWalkable() {
        return !water;
    }

    @Override
    public boolean hasTree() {
        return tree > 0;
    }

    @Override
    public boolean hasStone() {
        return stone > 0;
    }

    @Override
    public boolean hasTwig() {
        return twig > 0;
    }

    @Override
    public boolean hasBerry() {
        return berry > 0;
    }

    @Override
    public boolean hasCarrot() {
        return carrot > 0;
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
        fire = state ? FIRE_DURATION : 0;
    }

    @Override
    public void setStone(boolean state) {
        stone = state ? STONE_DURATION : 0;
    }

    @Override
    public void setTree(boolean state) {
        tree = state ? TREE_DURATION : 0;
    }

    @Override
    public void setTwig(boolean state) {
        twig = state ? TWIG_DURATION : 0;
    }

    @Override
    public void setBerry(boolean state) {
        berry = state ? BERRY_DURATION : 0;
    }

    @Override
    public void setCarrot(boolean state) {
        carrot = state ? CARROT_DURATION : 0;
    }

    @Override
    public AbstractItem interact() {
        if (isEmpty()) {
            return null;
        }
        if (hasTree()) {
            tree--;
            if (hasTree()) {
                return new ItemLog(2);
            }
        }
        if (hasStone()) {
            stone--;
            if (hasStone()) {
                return new ItemStone(3);
            }
        }
        if (hasTwig()) {
            twig--;
            if (hasTwig()) {
                return new ItemTwig(1);
            }
        }
        if (hasBerry()) {
            berry--;
            if (hasBerry()) {
                return new ItemRawBerry(1);
            }
        }
        if (hasCarrot()) {
            carrot--;
            if (hasCarrot()) {
                return new ItemRawCarrot(1);
            }
        }
        return null;
    }

    @Override
    public void tick() {
        if (fire > 0) {
            fire--;
        }
    }

}
