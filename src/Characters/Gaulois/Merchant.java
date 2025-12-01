package src.Characters;

import src.Interfaces.Worker;

public class Merchant extends Character implements Worker {

    public Merchant(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {
        System.out.println(this.getName() + " est en train de travailler.");
    }
}
