package src.characters.romain;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.ILeader;

public class Prefect extends src.characters.Character implements ILeader {

    public Prefect(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void lead(Character character) {
        System.out.println("[Prefet] " + this.getName() + " donne un ordre à " + character.getName());
    }
}
