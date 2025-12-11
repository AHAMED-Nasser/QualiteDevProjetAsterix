package src.characters.gaulois;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.IWorker;

public class Merchant extends Character implements IWorker {

    public Merchant(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void work() {
        System.out.println("[Marchand] " + this.getName() + " est en train de travailler.");
    }
}
