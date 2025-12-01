package src.Characters.Romain;

import src.Characters.Character;
import src.Interfaces.ILeader;

public class Prefect extends src.Characters.Character implements ILeader {

    public Prefect(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void lead(Character character) {
        System.out.println("[Prefet] " + this.getName() + " donne un ordre à " + character.getName());
    }
}
