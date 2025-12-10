package src.Characters.Romain;

import src.Characters.Character;
import src.Enum.Character.Faction;
import src.Interfaces.Character.IFighter;
import src.Interfaces.Character.ILeader;

public class General extends src.Characters.Character implements IFighter, ILeader {

    public General(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void fight(src.Characters.Character character) {
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
