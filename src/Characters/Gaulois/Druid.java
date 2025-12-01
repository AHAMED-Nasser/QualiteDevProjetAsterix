package src.Characters.Gaulois;

import src.Characters.Character;
import src.Characters.Statistics;
import src.Interfaces.IFighter;
import src.Interfaces.ILeader;
import src.Interfaces.IWorker;

public class Druid extends src.Characters.Character implements IWorker, ILeader, IFighter {

    private final int MAX_MAGIC_POTION = 5;
    private Statistics magicPotionBottle = new Statistics(0, 0, MAX_MAGIC_POTION);
    private final Statistics magicPotionCauldron = new Statistics(0, 0, 1);

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

    public Statistics getMagicPotionBottle() { return this.magicPotionBottle; }
    public void setMagicPotionBottle(Statistics nbMagicPotionBottle) { this.magicPotionBottle = nbMagicPotionBottle; }

    public void createMagicPotion() {
        this.magicPotionCauldron.add(1); // On créé un chaudron max
        this.magicPotionBottle.add(5); // On créé cinq récipiant de potion magique

        System.out.println("[Druide] " + this.getName() + " à créé une marmite de potion magique : " + this.magicPotionCauldron.get());
        System.out.println(this.getNbMagicPotionBottle() + " récipiant de potion magique on été créé.");
    }

    public boolean hasMagicPotionCauldron() { return this.magicPotionCauldron.get() > 0; }
    public boolean hasMagicPotionBottle() { return this.magicPotionBottle.get() > 0; }
    public int getNbMagicPotionBottle() { return this.magicPotionBottle.get(); }
}
