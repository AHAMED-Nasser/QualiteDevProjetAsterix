package src.Characters;

import src.Interfaces.Fighter;
import src.Interfaces.Leader;
import src.Interfaces.Worker;

public class Druid extends Character implements Worker, Leader, Fighter {

    public Druid(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {}

    @Override
    public void lead(Character character) {
        System.out.println(this.getName() + " donne un ordre à " + character.getName());
    }

    @Override
    public void fight(Character character) {
        System.out.println(this.getName() + " se battre contre " + character.getName());
    }
}
