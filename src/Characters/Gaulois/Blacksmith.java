package src.Characters;

import src.Interfaces.Worker;

public class Blacksmith extends Character implements Worker {

    public Blacksmith(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {
        System.out.println(this.getName() + " est en train de travailler.");
    }

}
