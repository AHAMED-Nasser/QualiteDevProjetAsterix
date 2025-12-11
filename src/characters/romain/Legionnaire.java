package src.characters.romain;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.IFighter;

public class Legionnaire extends src.characters.Character implements IFighter {

    public Legionnaire(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void fight(Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi-même ?");
            return;
        }
        System.out.println("[Légionnaire] " + this.getName() + " se bat contre " + character.getName());
    }
}
