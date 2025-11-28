package src.Characters;

import src.AbstractClass.AbstractPlayer;

public class Romain extends AbstractPlayer {
    private String rank;

    public Romain(String name, String sex, double height, int age, int strength, int stamina, int health, int hunger, int belligerence, int levelMagicPotion, String rank) {
        super(name, sex, height, age, strength, stamina, health, hunger, belligerence, levelMagicPotion);
        this.rank = rank;
    }

    @Override
    public void drinkMagicPotion() {
        System.out.println(this.getName() + ", Romain, ne boit pas cette sorcellerie gauloise !");
    }
}
