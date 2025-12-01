package src.Characters.Gaulois;

import src.Characters.Character;
import src.Interfaces.IFighter;
import src.Interfaces.ILeader;
import src.Interfaces.IWorker;

public class Druid extends src.Characters.Character implements IWorker, ILeader, IFighter {

    public Druid(String name, char sex, int height, int age, int strength, int stamina) {
        super(name, sex, height, age, strength, stamina);
    }

    @Override
    public void work() {
        System.out.println("[Druide] " + this.getName() + " est en train de travailler.");
    }

    @Override
    public void lead(src.Characters.Character character) {
        System.out.println("[Druide] " + this.getName() + " donne un ordre à " + character.getName());
    }

    @Override
    public void fight(Character character) {
        System.out.println("[Druide] " + this.getName() + " se battre contre " + character.getName());
    }
}
