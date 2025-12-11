package src.main.java.characters.gaulois;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IWorker;

public class Merchant extends Character implements IWorker {

    /**
     * Merchant constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
    public Merchant(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void work() {
        System.out.println("[Marchand] " + this.getName() + " est en train de travailler.");
    }
}
