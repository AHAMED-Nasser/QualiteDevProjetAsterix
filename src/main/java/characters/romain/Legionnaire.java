package src.main.java.characters.romain;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IFighter;

public class Legionnaire extends src.main.java.characters.Character implements IFighter {

    /**
     * Legionnaire constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
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
