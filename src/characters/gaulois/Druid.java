package src.characters.gaulois;

import src.characters.Character;
import src.Enum.character.Faction;
import src.characters.IFighter;
import src.characters.ILeader;
import src.characters.IWorker;

public class Druid extends src.characters.Character implements IWorker, ILeader, IFighter {

    public Druid(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    @Override
    public void work() {
        System.out.println("[Druide] " + this.getName() + " est en train de travailler.");
    }

    @Override
    public void lead(src.characters.Character character) {
        System.out.println("[Druide] " + this.getName() + " donne un ordre à " + character.getName());
    }

    @Override
    public void fight(Character character) {
        System.out.println("[Druide] " + this.getName() + " se battre contre " + character.getName());
    }
}
