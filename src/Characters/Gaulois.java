package src.Characters;

import src.AbstractClass.AbstractPlayer;

public class Gaulois extends AbstractPlayer {
    private String profession;

    private int potionStock;

    public Gaulois(String name, String sex, double height, int age, int strength, int stamina, int health, int hunger, int belligerence, int levelMagicPotion, String profession) {
        super(name, sex, height, age, strength, stamina, health, hunger, belligerence, levelMagicPotion);
        this.profession = profession;
        this.potionStock = profession.equalsIgnoreCase("druide") ? 10 : 0;
    }

    public String getProfession() { return this.profession; }

    @Override
    public void drinkMagicPotion() {
        super.drinkMagicPotion();

        // Strength boost
        if (this.getLevelMagicPotion() > 0) {
            int boost = 50 * this.getLevelMagicPotion();
            System.out.println(this.getName() + " est boosté par la potion ! Force temporaire : " + (this.getStrength() + boost));
        }
    }

    public void removePotionEffect() {
        this.setLevelMagicPotion(0);
        System.out.println("😴 L'effet de la potion sur " + this.getName() + " est terminé. Force rétablie à " + this.getStrength() + ".");
    }
}
