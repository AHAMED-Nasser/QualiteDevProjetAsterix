package src.Characters.Romain;

import src.Characters.Character;
import src.Interfaces.IFighter;

public class Legionnaire extends src.Characters.Character implements IFighter {

    public Legionnaire(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void fight(Character character) {
        if (this.getName().equals(character.getName())) {
            System.out.println("Pourquoi je me bat contre moi-même ?");
            return;
        }
        System.out.println("[Légionnaire] " + this.getName() + " se bat contre " + character.getName());
    }
}
