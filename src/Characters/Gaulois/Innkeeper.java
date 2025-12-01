package src.Characters.Gaulois;

import src.Characters.Character;
import src.Interfaces.IWorker;

public class Innkeeper extends Character implements IWorker {

    public Innkeeper(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    public void work() {
        System.out.println("[Aubergiste] " + this.getName() + " est en train de travailler.");
    }
}
