package src.Characters;

import src.Interfaces.ICharacterAction;

public abstract class Character implements ICharacterAction {

    // Basic stats
    private String name;
    private char sex;
    private int height; // in meters
    private int age;
    private int strength;
    private int stamina;

    // Fight stats
    private Statistics health = new Statistics(100, 0, 100); // 0 = death, 100 = top form
    private Statistics hunger = new Statistics(100, 0, 100); // 0 = hungry, 100 = satiated
    private Statistics belligerence = new Statistics(100, 0, 100); // 0 = pacific, 100 = aggressive
    private Statistics magicPotion = new Statistics(0, 0, 100);


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
    public Statistics getHealth() { return this.health; }
    public Statistics getHunger() { return this.hunger; }
    public Statistics getBelligerence() { return this.belligerence; }
    public Statistics getMagicPotion() { return this.magicPotion; }

    //--- Setters ---
    protected void setHealth(Statistics health) { this.health = health; }
    protected void setHunger(Statistics hunger) { this.hunger = hunger; }
    protected void setMagicPotion(Statistics magicPotion) { this.magicPotion = magicPotion; }

    @Override
    public void takeDamage(int damage) {
        if (this.isDead()) return;

        this.health.add(-damage);

        System.out.println(this.name + " a subit " + damage + " point de dégâts. Santé restant : " + this.health.get());

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
