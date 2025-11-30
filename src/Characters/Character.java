package src.Characters;

import src.Interfaces.ICharacterAction;
import src.Characters.CharacterStats;

public abstract class Character implements ICharacterAction {

    // Basic stats
    private String name;
    private char sex;
    private int height; // in meters
    private int age;
    private int strength;
    private int stamina;

    // Fight stats
    private CharacterStats health = new CharacterStats(100, 0, 100); // 0 = death, 100 = top form
    private CharacterStats hunger = new CharacterStats(100, 0, 100); // 0 = hungry, 100 = satiated
    private CharacterStats belligerence = new CharacterStats(100, 0, 100); // 0 = pacific, 100 = aggressive
    private CharacterStats magicPotion = new CharacterStats(0, 0, 100);

    public Character(String name, char sex, int height, int age, int strength, int stamina) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.stamina = stamina;
    }

    //--- Getters ---
    public String getName() { return this.name; }
    public char getSex() { return this.sex; }
    public int getHeight() { return this.height; }
    public int getAge() { return this.age; }
    public int getStrength() { return this.strength; }
    public int getStamina() { return this.stamina; }
    public CharacterStats getHealth() { return this.health; }
    public CharacterStats getHunger() { return this.hunger; }
    public CharacterStats getBelligerence() { return this.belligerence; }
    public CharacterStats getLevelMagicPotion() { return this.magicPotion; }

    //--- Setters ---
    protected void setHealth(CharacterStats health) { this.health = health; }
    protected void setHunger(CharacterStats hunger) { this.hunger = hunger; }
    protected void setMagicPotion(CharacterStats magicPotion) { this.magicPotion = magicPotion; }

    @Override
    public void takeDamage(int damage) {
        if (this.isDead()) return;

        this.health.add(-damage);

        System.out.println(this.name + " a subit " + damage + " point de dégâts. Santé restante : " + this.health.get());

        isDead(); // Verify if the player die after the fight
    }

    @Override
    public void heal(int amount) {
        if (this.isDead()) return;

        this.health.add(amount);

        System.out.println(this.name + " se soigne de " + amount + " points. Santé actuelle : " + this.health);
    }

    @Override
    public void eat(int hungerAmount) {
        if (this.isDead()) return;

        this.hunger.add(hungerAmount);

        System.out.println(this.name + " mange. Faim actuelle : " + this.hunger.get());
    }

    @Override
    public void drinkMagicPotion(int potionAmount) {
        if (this.isDead()) return;

        this.magicPotion.add(potionAmount);
        System.out.println(this.name + " boit de la potion magique. Niveau de potion : " + this.magicPotion);
    }

    @Override
    public boolean isDead() {
        return this.health.get() <= 0;
    }

}
