package src.Characters;

import src.Interfaces.Fighter;
import src.Interfaces.Leader;

public class General extends Character implements Fighter, Leader {

    public General(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void fight(Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi même ?");
            return;
        }
        System.out.println(this.getName() + " se bat " + character.getName());
    }

    @Override
    public void lead(Character character) {
        System.out.println(this.getName() + " donne un ordre à " + character.getName());
    }
}
