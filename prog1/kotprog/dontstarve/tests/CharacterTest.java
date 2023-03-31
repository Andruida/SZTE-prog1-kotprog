package prog1.kotprog.dontstarve.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import prog1.kotprog.dontstarve.solution.character.Character;
import prog1.kotprog.dontstarve.solution.utility.Position;

public class CharacterTest {

    @Test
    @DisplayName("Character property check, clamping")
    void characterPlain() {
        final Character character = new Character("player", new Position(0, 0));
        assertEquals(100f, character.getHp());
        assertEquals(100f, character.getHunger());

        character.setHp(110);
        character.setHunger(110);

        assertEquals(100f, character.getHp());
        assertEquals(100f, character.getHunger());

        character.setHp(90);
        character.setHunger(90);

        assertEquals(90f, character.getHp());
        assertEquals(90f, character.getHunger());

        character.setHp(-10);
        character.setHunger(-10);

        assertEquals(0f, character.getHp());
        assertEquals(0f, character.getHunger());
    }
    
}
