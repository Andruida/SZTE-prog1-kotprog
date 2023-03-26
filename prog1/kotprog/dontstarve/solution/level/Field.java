package prog1.kotprog.dontstarve.solution.level;

import java.util.ArrayList;
import java.util.List;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

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
     * A mezőn található-e tűz.
     */
    private boolean fire;

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
        fire = false;
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
                itemList.add(new ItemTwig(1));
                break;
            case MapColors.BERRY:
                itemList.add(new ItemRawBerry(1));
                break;
            case MapColors.CARROT:
                itemList.add(new ItemRawCarrot(1));
                break;
            case MapColors.EMPTY:
            default:
                break;
        }
    }

    /**
     * Metódus, ami megadja, hogy az adott típusú itemből van-e a mezőn.
     *
     * @param itemType az item, amiről lekérdezzük, hogy van-e a mezőn
     * @return true, ha van ilyen item a mezőn, false, ha nincs
     */
    private boolean hasItem(ItemType itemType) {
        for (AbstractItem i : itemList) {
            if (i.getType() == itemType) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metódus, ami megadja, hogy a mező üres-e.
     * @return true, ha üres, false, ha nem
     */
    public boolean isEmpty() {
        return !water && !tree && !stone && !fire && itemList.isEmpty();
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
        return hasItem(ItemType.TWIG);
    }

    @Override
    public boolean hasBerry() {
        return hasItem(ItemType.RAW_BERRY);
    }

    @Override
    public boolean hasCarrot() {
        return hasItem(ItemType.RAW_CARROT);
    }

    @Override
    public boolean hasFire() {
        return fire;
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

}
