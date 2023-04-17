package prog1.kotprog.dontstarve.solution.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionAttack;
import prog1.kotprog.dontstarve.solution.character.actions.ActionCollectItem;
import prog1.kotprog.dontstarve.solution.character.actions.ActionCraft;
import prog1.kotprog.dontstarve.solution.character.actions.ActionInteract;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.character.actions.ActionStep;
import prog1.kotprog.dontstarve.solution.character.actions.ActionStepAndAttack;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.utility.Direction;

public class Brain {

    private MutableCharacter character;

    public Brain(MutableCharacter character) {
        this.character = character;
    }

    public Action think() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ActionNone());
        actions.add(new ActionAttack());
        actions.add(new ActionCollectItem());
        actions.add(new ActionCraft(ItemType.AXE));
        actions.add(new ActionCraft(ItemType.PICKAXE));
        actions.add(new ActionCraft(ItemType.PICKAXE));
        actions.add(new ActionCraft(ItemType.TORCH));
        actions.add(new ActionInteract());
        actions.add(new ActionStep(Direction.UP));
        actions.add(new ActionStep(Direction.DOWN));
        actions.add(new ActionStep(Direction.LEFT));
        actions.add(new ActionStep(Direction.RIGHT));
        actions.add(new ActionStepAndAttack(Direction.UP));
        actions.add(new ActionStepAndAttack(Direction.DOWN));
        actions.add(new ActionStepAndAttack(Direction.LEFT));
        actions.add(new ActionStepAndAttack(Direction.RIGHT));
        Random random = GameManager.getInstance().getRandom();

        return actions.get(random.nextInt(actions.size()));
    }
    
}
