package src.main.java.characters.gaulois;

import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IFighter;
import src.main.java.characters.ILeader;
import src.main.java.characters.IWorker;

public class Druid extends src.main.java.characters.Character implements IWorker, ILeader, IFighter {

    public Druid(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void work() {
        System.out.println("[Druide] " + this.getName() + " est en train de travailler.");
    }

    @Override
    public void lead(src.main.java.characters.Character character) {
        System.out.println("[Druide] " + this.getName() + " donne un ordre à " + character.getName());
    }

    @Override
    public void fight(Character character) {
        System.out.println("[Druide] " + this.getName() + " se battre contre " + character.getName());
    }
}
