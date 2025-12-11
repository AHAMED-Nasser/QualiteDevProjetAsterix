package src.main.java.characters.fantastique;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IFighter;

public class Lycanthrope extends src.main.java.characters.Character implements IFighter {

    public Lycanthrope(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void fight(Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi-même ?");
            return;
        }
        System.out.println("[Lycanthrop] " + this.getName() + " se bat contre " + character.getName());
    }
}
