package src.Characters.Gaulois;

import src.Characters.Character;
import src.Interfaces.IWorker;

public class Merchant extends Character implements IWorker {

    public Merchant(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {
        System.out.println("[Marchand] " + this.getName() + " est en train de travailler.");
    }
}
