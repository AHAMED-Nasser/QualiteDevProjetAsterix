package src.main.java.characters.gaulois;

import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;
import src.main.java.characters.Character;
import src.main.java.Enum.character.Faction;
import src.main.java.characters.IFighter;
import src.main.java.characters.ILeader;
import src.main.java.characters.IWorker;
import src.main.java.food.Food;
import src.main.java.items.Cauldron;
import src.main.java.place.Place;

import java.util.ArrayList;
import java.util.List;

public class Druid extends src.main.java.characters.Character implements IWorker, ILeader, IFighter {

    /**
     * Druid constructor
     * @param name
     * @param sex
     * @param height
     * @param age
     * @param strength
     * @param stamina
     * @param faction
     */
    public Druid(String name, char sex, int height, int age, int strength, int stamina, Faction faction) {
        super(name, sex, height, age, strength, stamina, faction);
    }

    /**
     * Brew of the magic potion cauldron
     * @param place
     * @param cauldron
     */
    public void brewPotion(Place place, Cauldron cauldron) {
        if (!cauldron.isEmpty()) return;

        System.out.println(this.getName() + " tente de préparer la potion magique ...");
        List<Food> stock = place.getFoodList();
        List<Food> ingredientsToRemove = new ArrayList<>();

        // Liste des ingrédients requis
        FoodType[] requiredTypes = {
            FoodType.MISTLETOE,         // Gui
            FoodType.CARROT,            // Carottes
            FoodType.SALT,              // Sel
            FoodType.FOUR_LEAF_CLOVER,  // Trèfle à quatre feuilles (Doit être FRAIS)
            FoodType.FAIRLY_FRESH_FISH, // Poisson passablement frais
            FoodType.ROCK_OIL,          // Huile de roche
            FoodType.HONEY,             // Miel
            FoodType.MEAD,              // Hydromel
            FoodType.SECRET_INGREDIENT  // Ingrédient secret
        };

        for (FoodType type : requiredTypes) {
            Food found = null;
            for (Food f : stock) {
                if (f.getFoodType() == type && !ingredientsToRemove.contains(f)) {
                    if (type == FoodType.FOUR_LEAF_CLOVER && f.getFoodFreshness() != FoodFreshness.FRESH) {
                        continue; // Ce trèfle n'est pas frais, on cherche un autre
                    }
                    found = f;
                    break;
                }
            }

            if (found == null) {
                System.out.println("  ❌ Échec : Il manque " + type);
                return;
            }
            ingredientsToRemove.add(found);
        }

        // Recherche des ingrédient optionnel
        boolean addUnicorn = false;
        boolean addIdefix = false;

        // Lait de licorne
        for (Food f : stock) {
            if (f.getFoodType() == FoodType.UNICOR_MILK && !ingredientsToRemove.contains(f)) {
                addUnicorn = true;
                ingredientsToRemove.add(f);
                System.out.println("   🦄 Ingrédient rare ajouté : Lait de licorne !");
                break;
            }
        }

        // Poils d'idéfix
        for (Food f : stock) {
            if (f.getFoodType() == FoodType.IDEFIX_HAIR && !ingredientsToRemove.contains(f)) {
                addIdefix = true;
                ingredientsToRemove.add(f);
                System.out.println("   🐶 Ingrédient rare ajouté : Poils d'Idéfix !");
                break;
            }
        }

        // LA CUISINE (let him cook)
        for (Food f : ingredientsToRemove) {
            place.getFoodList().remove(f);
        }

        cauldron.fill(addUnicorn, addIdefix);
    }

    @Override
    public void work() {
        System.out.println("[Druide] " + this.getName() + " est en train de travailler.");
    }

    @Override
    public void lead(src.main.java.characters.Character character) {
        System.out.println("[Druide] " + this.getName() + " donne un ordre à " + character.getName());
    }

    @Override
    public void fight(Character character) {
        System.out.println("[Druide] " + this.getName() + " se battre contre " + character.getName());
    }
}
