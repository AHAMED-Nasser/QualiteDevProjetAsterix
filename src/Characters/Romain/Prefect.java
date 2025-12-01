package src.Characters;

import src.Interfaces.Leader;

public class Prefect extends Character implements Leader {

    public Prefect(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void lead(Character character) {
        System.out.println(this.getName() + " donne un ordre à " + character.getName());
    }
}
