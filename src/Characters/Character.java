package src.Characters;

import src.Enum.Character.Faction;
import src.Enum.Food.FoodCategory;
import src.Enum.Food.FoodFreshness;
import src.Enum.Food.FoodType;
import src.Food.Food;
import src.Interfaces.ICharacterAction;
import src.Statistics;

import java.util.ArrayList;
import java.util.List;

public abstract class Character implements ICharacterAction {

    // Basic stats
    private String name;
    private char sex;
    private int height; // in meters
    private int age;
    private int strength;
    private int stamina;
    private Faction faction;

    // Fight stats
    private Statistics health = new Statistics(100, 0, 100); // 0 = death, 100 = top form
    private Statistics hunger = new Statistics(100, 0, 100); // 0 = hungry, 100 = satiated
    private Statistics belligerence = new Statistics(100, 0, 100); // 0 = pacific, 100 = aggressive
    private Statistics magicPotion = new Statistics(0, 0, 100);

    private final List<FoodType> gaulsFoods = new ArrayList<>();
    private final List<FoodType> romanFoods = new ArrayList<>();

    public Character(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.stamina = stamina;
        this.faction = faction;

        gaulsFoods.add(FoodType.WILD_BOAR);
        gaulsFoods.add(FoodType.WINE);
        gaulsFoods.add(FoodType.FAIRLY_FRESH_FISH);

        romanFoods.add(FoodType.WILD_BOAR);
        romanFoods.add(FoodType.HONEY);
        romanFoods.add(FoodType.WINE);
        romanFoods.add(FoodType.MEAD);
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
    public Faction getFaction() { return this.faction; }

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

        System.out.println(this.name + " se soigne de " + amount + " points. Santé actuelle : " + this.health.get());
    }

    private int nb_vegetables = 0; // Nombre de végétaux mangé
    @Override
    public void eat(Food food) {
        if (this.isDead()) return;

        int hunger_win = 0;
        int pv_win = 0;

        if (food.getFoodCategory() == FoodCategory.VEGETABLE) {
            hunger_win = food.getFoodNutrition() / 2;
            pv_win = food.getFoodNutrition();
            if (nb_vegetables >= 2) nb_vegetables = 0;
            nb_vegetables++;
        }

        if (food.getFoodFreshness() == FoodFreshness.NOT_FRESH) { // Si nourriture pas frais
            int hunger_lost_point = food.getFoodNutrition();
            int pv_lost = food.getFoodNutrition() / 2; // on perd
            System.out.println(this.name + " à mangé " + food.getName() + "." + hunger_lost_point + " points de faim et " + pv_lost + " points de vie.");
            this.hunger.add(hunger_lost_point); // On retire la valeur de la nutrition mais pas trop
            this.takeDamage(pv_lost); // On pert des pv
        } else if (nb_vegetables >= 2) { // si plus de deux végétaux consommé
            int hunger_lost_point = -40;
            int pv_lost = food.getFoodNutrition() - (food.getFoodNutrition() / 2);
            System.out.println(this.name + " à mangé deux végétaux consecutivement." + hunger_lost_point + " points de faim et +" + pv_lost + " points de vie.");
            this.hunger.add(hunger_lost_point);
            this.takeDamage(pv_lost);
            System.out.println("Aaah... je ne me sens pas bien...");
        } else { // Sinon on prend des pv et point de faim
            if (this.faction == Faction.GAULS && gaulsFoods.contains(food.getFoodType())) {
                hunger_win = food.getFoodNutrition();
            } else if (this.faction == Faction.ROMAN && romanFoods.contains(food.getFoodType())) {
                hunger_win = food.getFoodNutrition();
            }
            System.out.println(this.name + " à mangé " + food.getName() + ". +" + hunger_win + " points de faim et +" + pv_win + " points de vie.");
            this.hunger.add(hunger_win);
            this.heal(pv_win);
        }
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

    @Override
    public void displayInformation() {
        System.out.println("<-------- Information de " + this.getName() +" -------->");
        System.out.println("Nom: " + this.name + " | Age: " + this.age + " | " + this.sex);
        System.out.println("Faction: " + this.faction);
        System.out.println("Force: " + this.strength);
        System.out.println("Endurance: " + this.stamina);
        System.out.println("Point de vie: " + this.health.get() + "/" + this.health.getMax());
        System.out.println("Niveau de faim: " + this.hunger.get() + "/" + this.hunger.getMax());
        System.out.println("Belligérance: " + this.belligerence.get() + "/" + this.belligerence.getMax());
        System.out.println("Niveau de potion magique: " + this.magicPotion.get() + "/" + this.magicPotion.getMax());
        System.out.println("<----------------------------------------->");
    }
}
