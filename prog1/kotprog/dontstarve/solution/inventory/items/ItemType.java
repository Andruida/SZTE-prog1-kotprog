package prog1.kotprog.dontstarve.solution.inventory.items;

import java.util.HashMap;
import java.util.Map;

/**
 * A tárgy típusok enumja.
 */
public enum ItemType {
    /**
     * fejsze.
     */
    AXE,

    /**
     * csákány.
     */
    PICKAXE,

    /**
     * lándzsa.
     */
    SPEAR,

    /**
     * fáklya.
     */
    TORCH,

    /**
     * farönk.
     */
    LOG,

    /**
     * kő.
     */
    STONE,

    /**
     * gally.
     */
    TWIG,

    /**
     * nyers bogyó.
     */
    RAW_BERRY,

    /**
     * nyers répa.
     */
    RAW_CARROT,

    /**
     * főtt bogyó.
     */
    COOKED_BERRY,

    /**
     * főtt répa.
     */
    COOKED_CARROT,

    /**
     * tábortűz (inventory-ban nem tárolható!).
     */
    FIRE;

    public Map<ItemType, Integer> getRecipe() {
        Map<ItemType, Integer> recipe = new HashMap<>();
        switch (this) {
            case AXE:
                recipe.put(TWIG, 3);
                break;
            case PICKAXE:
                recipe.put(LOG, 2);
                recipe.put(TWIG, 2);
                break;
            case SPEAR:
                recipe.put(LOG, 2);
                recipe.put(STONE, 2);
                break;
            case TORCH:
                recipe.put(LOG, 1);
                recipe.put(TWIG, 3);
                break;
            case FIRE:
                recipe.put(TWIG, 2);
                recipe.put(LOG, 2);
                recipe.put(STONE, 4);
                break;

            default:
                return null;
        }

        return recipe;
    }

    /**
     * Visszaadja a tárgy típusnak megfelelő tárgy példányt.
     * @param amount a tárgy mennyisége
     * @return a tárgy példánya
     */
    public AbstractItem instantiate(int amount) {
        switch(this) {
            case AXE:
                return new ItemAxe();
            case COOKED_BERRY:
                return new ItemCookedBerry(amount);
            case COOKED_CARROT:
                return new ItemCookedCarrot(amount);
            case FIRE:
                return new ItemFire();
            case LOG:
                return new ItemLog(amount);
            case PICKAXE:
                return new ItemPickaxe();
            case RAW_BERRY:
                return new ItemRawBerry(amount);
            case RAW_CARROT:
                return new ItemRawCarrot(amount);
            case SPEAR:
                return new ItemSpear();
            case STONE:
                return new ItemStone(amount);
            case TORCH:
                return new ItemTorch();
            case TWIG:
                return new ItemTwig(amount);
            default:
                return null;

        }
    }

    /**
     * Visszaadja a tárgy típusnak megfelelő tárgy példányt.
     * @return a tárgy példánya (mennyiség=1)
     */
    public AbstractItem instantiate() {
        return instantiate(1);
    }
}
