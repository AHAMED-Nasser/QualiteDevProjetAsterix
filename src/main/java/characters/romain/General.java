package src.main.java.characters.romain;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IFighter;
import src.main.java.characters.ILeader;

public class General extends src.main.java.characters.Character implements IFighter, ILeader {

    public General(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void fight(src.main.java.characters.Character character) {
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
