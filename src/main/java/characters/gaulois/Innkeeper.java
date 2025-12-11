package src.main.java.characters.gaulois;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IWorker;

public class Innkeeper extends Character implements IWorker {

    /**
     * Innkeeper constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
    public Innkeeper(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    public void work() {
        System.out.println("[Aubergiste] " + this.getName() + " est en train de travailler.");
    }
}
