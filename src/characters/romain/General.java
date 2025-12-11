package src.characters.romain;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.IFighter;
import src.characters.ILeader;

public class General extends src.characters.Character implements IFighter, ILeader {

    public General(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void fight(src.characters.Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi même ?");
            return;
        }
        System.out.println("[Général] " + this.getName() + " se bat " + character.getName());
    }

    @Override
    public void lead(Character character) {
        System.out.println("[Général] " + this.getName() + " donne un ordre à " + character.getName());
    }
}
