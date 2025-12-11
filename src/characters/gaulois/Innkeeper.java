package src.characters.gaulois;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.IWorker;

public class Innkeeper extends Character implements IWorker {

    public Innkeeper(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    public void work() {
        System.out.println("[Aubergiste] " + this.getName() + " est en train de travailler.");
    }
}
