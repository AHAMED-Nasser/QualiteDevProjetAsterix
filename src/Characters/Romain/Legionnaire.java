package src.Characters;

import src.Interfaces.Fighter;

public class Legionnaire extends Character implements Fighter {

    public Legionnaire(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void fight(Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi-même ?");
            return;
        }
        System.out.println(this.getName() + " se bat contre " + character.getName());
    }
}
