package src.Characters.Gaulois;

import src.Characters.Character;
import src.Interfaces.IWorker;

public class Blacksmith extends Character implements IWorker {

    public Blacksmith(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {
        System.out.println("[Forgeron] " + this.getName() + " est en train de travailler.");
    }

}
