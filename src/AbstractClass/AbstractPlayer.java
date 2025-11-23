package src.AbstractClass;

public abstract class AbstractPlayer {

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

    // Limit
    private static final int MAX_INDICATOR = 100;
    private static final int CRITICAL_INDICATOR = 10;

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

        // eat
    }

}
