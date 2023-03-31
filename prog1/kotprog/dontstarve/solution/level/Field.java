package prog1.kotprog.dontstarve.solution.level;

import java.util.ArrayList;
import java.util.List;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

public class Field implements MutableField {

    /**
     * Fa kivágásának ideje.
     */
    private static final int TREE_DURATION = 4;

    /**
     * Kő kifejtésének ideje.
     */
    private static final int STONE_DURATION = 5;

    /**
     * Gally leszedésének ideje.
     */
    private static final int TWIG_DURATION = 2;

    /**
     * Bogyó leszedésének ideje.
     */
    private static final int BERRY_DURATION = 1;

    /**
     * Répa begyűjtésének ideje.
     */
    private static final int CARROT_DURATION = 1;

    /**
     * Tűz élettartama.
     */
    private static final int FIRE_DURATION = 60;

    /**
     * Mező típusa.
     */
    private FieldType fieldType;

    /**
     * A mezőn található erőforrás élettartama.
     */
    private int resourceDuration;

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
        fieldType = FieldType.EMPTY;
        resourceDuration = 0;
        fire = 0;
        itemList = new ArrayList<>();


        switch (color) {
            case MapColors.WATER:
                fieldType = FieldType.WATER;
                break;
            case MapColors.TREE:
                resourceDuration = TREE_DURATION;
                fieldType = FieldType.TREE;
                break;
            case MapColors.STONE:
                resourceDuration = STONE_DURATION;
                fieldType = FieldType.STONE;
                break;
            case MapColors.TWIG:
                resourceDuration = TWIG_DURATION;
                fieldType = FieldType.TWIG;
                break;
            case MapColors.BERRY:
                resourceDuration = BERRY_DURATION;
                fieldType = FieldType.BERRY;
                break;
            case MapColors.CARROT:
                resourceDuration = CARROT_DURATION;
                fieldType = FieldType.CARROT;
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
        return fieldType == FieldType.EMPTY
            && fire <= 0
            && itemList.isEmpty();
    }

    @Override
    public boolean isWalkable() {
        return fieldType != FieldType.WATER;
    }

    @Override
    public boolean hasTree() {
        return fieldType == FieldType.TREE;
    }

    @Override
    public boolean hasStone() {
        return fieldType == FieldType.STONE;
    }

    @Override
    public boolean hasTwig() {
        return fieldType == FieldType.TWIG;
    }

    @Override
    public boolean hasBerry() {
        return fieldType == FieldType.BERRY;
    }

    @Override
    public boolean hasCarrot() {
        return fieldType == FieldType.CARROT;
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
        resourceDuration = state ? STONE_DURATION : 0;
        fieldType = state ? FieldType.STONE : FieldType.EMPTY;
    }

    @Override
    public void setTree(boolean state) {
        resourceDuration = state ? TREE_DURATION : 0;
        fieldType = state ? FieldType.TREE : FieldType.EMPTY;
    }

    @Override
    public void setTwig(boolean state) {
        resourceDuration = state ? TWIG_DURATION : 0;
        fieldType = state ? FieldType.TWIG : FieldType.EMPTY;
    }

    @Override
    public void setBerry(boolean state) {
        resourceDuration = state ? BERRY_DURATION : 0;
        fieldType = state ? FieldType.BERRY : FieldType.EMPTY;
    }

    @Override
    public void setCarrot(boolean state) {
        resourceDuration = state ? CARROT_DURATION : 0;
        fieldType = state ? FieldType.CARROT : FieldType.EMPTY;
    }

    @Override
    public AbstractItem interact(EquippableItem tool) {
        if (isEmpty()) {
            return null;
        }
        if (resourceDuration <= 0) {
            return null;
        }
        if (hasTree() && (tool == null || tool.percentage() == 0 || tool.getType() != ItemType.AXE)) {
            return null;
        }
        if (hasStone() && (tool == null || tool.percentage() == 0 || tool.getType() != ItemType.PICKAXE)) {
            return null;
        }

        resourceDuration--;
        if (tool != null && (hasTree() || hasStone())) {
            tool.damage();
        }
        if (resourceDuration > 0) {
            return null;
        }

        AbstractItem loot = fieldType.getLoot();
        if (loot == null) {
            return null;
        }

        fieldType = FieldType.EMPTY;
        return loot;
    }

    @Override
    public void tick() {
        if (hasFire()) {
            fire--;
        }
    }

}
