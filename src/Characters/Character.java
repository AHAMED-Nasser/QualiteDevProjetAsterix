package src.AbstractClass;

import src.Interfaces.ICharacterAction;

public abstract class AbstractPlayer implements ICharacterAction {

    // Basic stats
    private String name;
    private String sex;
    private double height; // in meters
    private int age;

    // Fight stats
    private int strength;
    private int stamina;
    private int health; // 0 = death, 100 = top form
    private int hunger; // 0 = hungry, 100 = satiated
    private int belligerence; // 0 = pacific, 100 = aggressive
    private int levelMagicPotion;

    private boolean isAlive;

    // Limit
    private static final int MAX_INDICATOR = 100;
    private static final int CRITICAL_INDICATOR = 10;
    private static final int INITIAL_MAGIC_POTION = 0;

    public AbstractPlayer(String name, String sex, double height, int age, int strength, int stamina, int health, int hunger, int belligerence, int levelMagicPotion) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.stamina = stamina;

        this.health = Math.min(health, MAX_INDICATOR);
        this.hunger = Math.min(hunger, MAX_INDICATOR);
        this.belligerence = Math.min(belligerence, MAX_INDICATOR);
        this.levelMagicPotion = levelMagicPotion;

        this.isAlive = this.health > 0; // Init stat
    }

    //--- Getters ---
    public String getName() { return this.name; }
    public String getSex() { return this.sex; }
    public double getHeight() { return this.height; }
    public int getAge() { return this.age; }
    public int getStrength() { return this.strength; }
    public int getStamina() { return this.stamina; }
    public int getHealth() { return this.health; }
    public int getHunger() { return this.hunger; }
    public int getBelligerence() { return this.belligerence; }
    public int getLevelMagicPotion() { return this.levelMagicPotion; }
    public boolean isAlive() { return this.isAlive; }

    //--- Setters ---
    protected void setHealth(int health) { this.health = health; }
    protected void setHunger(int hunger) { this.hunger = hunger; }
    protected void setLevelMagicPotion(int levelMagicPotion) { this.levelMagicPotion = levelMagicPotion; }
    protected void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }

    @Override
    public void fight(AbstractPlayer opponent) {
        if (!this.isAlive || !opponent.isAlive()) {
            System.out.println(this.name + " ou " + opponent.getName() + " n'est pas apte au combat.");
            return;
        }

        System.out.println(this.name + " attaque " + opponent.getName() + " !");

        // damage = strength - (stamina / 2)
        int damageDealt = Math.max(0, this.strength - (opponent.getStamina() / 2));
        opponent.takeDamage(damageDealt);
    }

    @Override
    public void takeDamage(int damage) {
        if (!this.isAlive) return;

        int newHealth = this.health - damage;
        this.health = Math.max(0, newHealth);

        System.out.println(this.name + " subit " + damage + " dégâts. Santé restante : " + this.health);

        checkDeath(); // Verify if the player die after the fight
    }

    @Override
    public void heal(int amount) {
        if (!this.isAlive) return;

        int newHealth = this.health + amount;
        this.health = Math.min(MAX_INDICATOR, newHealth);
        System.out.println(this.name + " se soigne de " + amount + " points. Santé actuelle : " + this.health);
    }

    @Override
    public void eat() {
        if (!this.isAlive) return;

        int satietyGain = 25;
        int newHunger = this.hunger + satietyGain;
        this.hunger = Math.min(MAX_INDICATOR, newHunger);
        System.out.println(this.name + " mange. Faim actuelle : " + this.hunger);
    }

    @Override
    public void drinkMagicPotion() {
        if (!this.isAlive) return;

        this.levelMagicPotion++;
        System.out.println(this.name + " boit de la potion magique. Niveau de potion : " + this.levelMagicPotion);
    }

    @Override
    public void checkDeath() {
        if (this.health <= 0) {
            die();
        } else if (this.health <= CRITICAL_INDICATOR) {
            System.out.println("ATTENTION ! " + this.name + " est dans un état critique !");
        }
    }

    private void die() {
        if (this.isAlive) {
            this.isAlive = false;
            this.health = 0;
            System.out.println(this.name + " est mort...");
        }
    }
}
