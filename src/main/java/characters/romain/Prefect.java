package src.main.java.characters.romain;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.ILeader;

public class Prefect extends src.main.java.characters.Character implements ILeader {

    /**
     * Prefet constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
    public Prefect(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void lead(Character character) {
        System.out.println("[Prefet] " + this.getName() + " donne un ordre à " + character.getName());
    }
}
