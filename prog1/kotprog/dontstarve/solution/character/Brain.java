package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionAttack;
import prog1.kotprog.dontstarve.solution.character.actions.ActionCollectItem;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.character.actions.ActionStepAndAttack;

public class Brain {

    private MutableCharacter character;

    public Brain(MutableCharacter character) {
        this.character = character;
    }

    public Action think() {
        GameManager gameManager = GameManager.getInstance();
        MutableCharacter player = gameManager.getHumanPlayer();
        if (player != null) {
            if (character.getCurrentPosition().getNearestField().items().length == 0) {
                // walk towards player
                return new ActionStepAndAttack(character.getCurrentPosition().directionTo(player.getCurrentPosition()));
            } else {
                // pick up item
                return new ActionCollectItem();
            }
        }
        return new ActionAttack();
    }
    
}
