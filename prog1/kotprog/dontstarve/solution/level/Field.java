package prog1.kotprog.dontstarve.solution.level;

import java.util.ArrayList;
import java.util.List;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

public class Field implements BaseField {
    
    /**
     * A mezőn található-e víz.
     */
    private boolean hasWater;

    /**
     * A mezőn található-e fa.
     */
    private boolean hasTree;

    /**
     * A mezőn található-e kő.
     */
    private boolean hasStone;

    /**
     * A mezőn található-e tűz.
     */
    private boolean hasFire;

    /**
     * A mezőn található itemek.
     */
    private List<AbstractItem> items;

    /**
     * Konstruktor.
     * @param color a mező színe a térképen
     */
    public Field(int color) {
        hasWater = false;
        hasTree = false;
        hasStone = false;
        hasFire = false;
        items = new ArrayList<>();


        switch (color) {
            case MapColors.WATER:
                hasWater = true;
                break;
            case MapColors.TREE:
                hasTree = true;
                break;
            case MapColors.STONE:
                hasStone = true;
                break;
            case MapColors.TWIG:
                items.add(new ItemTwig(1));
                break;
            case MapColors.BERRY:
                items.add(new ItemRawBerry(1));
                break;
            case MapColors.CARROT:
                items.add(new ItemRawCarrot(1));
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
        for (AbstractItem i : items) {
            if (i.getType() == itemType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWalkable() {
        return !hasWater;
    }

    @Override
    public boolean hasTree() {
        return hasTree;
    }

    @Override
    public boolean hasStone() {
        return hasStone;
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
        return hasFire;
    }

    @Override
    public AbstractItem[] items() {
        return items.toArray(new AbstractItem[items.size()]);
    }

}
