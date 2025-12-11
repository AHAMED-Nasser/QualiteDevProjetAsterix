package src.main.java.characters;

import src.main.java.Enum.character.Faction;
import src.main.java.Enum.food.FoodCategory;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;
import src.main.java.food.Food;
import src.main.java.Statistics;

import java.util.Arrays;
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

    private final List<FoodType> gaulsFoods = Arrays.asList(
            FoodType.WILD_BOAR, FoodType.WINE, FoodType.FAIRLY_FRESH_FISH
    );
    private final List<FoodType> romanFoods = Arrays.asList(
            FoodType.WILD_BOAR, FoodType.HONEY, FoodType.WINE, FoodType.MEAD
    );

    public Character(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.age = age;
        this.strength = strength;
        this.stamina = stamina;
        this.faction = faction;
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
    public void setHealth(Statistics health) { this.health = health; }
    public void setHunger(Statistics hunger) { this.hunger = hunger; }
    public void setMagicPotion(Statistics magicPotion) { this.magicPotion = magicPotion; }

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

    private int nb_vegetables = 0; // Assurez-vous que cette ligne est présente au début de la classe Character

    @Override
    public void eat(Food food) {
        if (this.isDead()) return;

        // --- 1. GESTION DES MALUS IMPÉRATIFS (Non refusables) ---

        // Malus 1: Nourriture pas fraîche (S'applique même si le personnage n'aime pas le reste)
        if (food.getFoodFreshness() == FoodFreshness.NOT_FRESH) {
            // CORRECTION: Utilisation de Math.abs() pour garantir des dégâts POSITIFS
            int pv_lost = Math.abs(food.getFoodNutrition() / 2); // Perte de PV
            int hunger_diminution = -15; // Petite perte de faim (arbitraire)

            System.out.println(this.name + " a mangé " + food.getName() + " pas frais. Perte de faim et dégâts.");
            this.hunger.add(hunger_diminution);
            this.takeDamage(pv_lost);

            nb_vegetables = 0; // Réinitialisation car ce n'est pas un légume consécutif valide
            return; // Fin de l'action, le personnage est malade et ne gagne rien d'autre.
        }

        // --- 2. DÉFINITION DES PRÉFÉRENCES ET GAINS DE BASE ---
        boolean isGaulPreferred = this.faction == Faction.GAULS && gaulsFoods.contains(food.getFoodType());
        boolean isRomanPreferred = this.faction == Faction.ROMAN && romanFoods.contains(food.getFoodType());
        boolean isVegetable = food.getFoodCategory() == FoodCategory.VEGETABLE;

        int hunger_gain = 0;
        int pv_gain = 0;

        if (isVegetable) {
            // Les légumes sont généralement acceptés
            hunger_gain = food.getFoodNutrition() / 2;
            pv_gain = food.getFoodNutrition();
        } else if (isGaulPreferred || isRomanPreferred) {
            // Aliments préférés
            hunger_gain = food.getFoodNutrition();
            pv_gain = food.getFoodNutrition();
        } else {
            // --- 3. GESTION DU REFUS PAR FACTION (Uniquement pour nourriture normale non préférée) ---
            if (this.faction == Faction.GAULS || this.faction == Faction.ROMAN) {
                System.out.println("Beurk, cette nourriture n'est pas adaptée à ma faction. Je ne mange pas ça.");
                nb_vegetables = 0;
                return; // Le personnage refuse et ne fait rien.
            }
        }

        // --- 4. GESTION DU COMPTEUR ET MALUS LÉGUMES CONSÉCUTIFS ---
        if (isVegetable) {
            nb_vegetables++;
        } else {
            nb_vegetables = 0;
        }

        if (isVegetable && nb_vegetables >= 2) {
            // Malus 2: Deuxième légume consécutif
            System.out.println("Aïe, " + this.name + " a mangé deux végétaux de suite et se sent mal.");
            this.takeDamage(10); // Dégâts fixe
            this.hunger.add(hunger_gain); // On est quand même nourri
        } else {
            // Cas normal : Application des gains
            System.out.println(this.name + " mange " + food.getName() + ". (Faim +" + hunger_gain + ", PV +" + pv_gain + ")");
            this.hunger.add(hunger_gain);
            this.heal(pv_gain);
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
    public void displayFullInformation() {
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

    @Override
    public void displayMinInformation() {
        System.out.println("--> Nom: " + this.name + " || Faction: " + this.faction + " || PV: " + this.health.get() + "/100 || Faim: " + this.hunger.get() + "/100");
    }
}
