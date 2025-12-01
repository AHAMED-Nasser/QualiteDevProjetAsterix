package src.Characters;

import src.Interfaces.Worker;

public class Innkeeper extends Character implements Worker {

    public Innkeeper(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    public void work() {
        System.out.println(this.getName() + " est en train de travailler.");
    }
}
