package src.Food;

import src.Statistics;

public class FoodFactory {

    public Food createFood(FoodType foodType) {
        return switch (foodType) {
            case WILD_BOAR -> new Food("Sanglier", 60, foodType, FoodFreshness.FRESH);
            case FISH -> new Food("Poisson frais", 40, foodType, FoodFreshness.FRESH);
            case MISTLETOE -> new Food("Gui", 10, foodType, FoodFreshness.FRESH);
            case LOBSTER -> new Food("Homard", 50, foodType, FoodFreshness.FRESH);
            case STRAWBERRY -> new Food("Fraise", 15, foodType, FoodFreshness.FRESH);
            case CARROT -> new Food("Carrot", 25, foodType, FoodFreshness.FRESH);
            case SALT -> new Food("Sel", 5, foodType, FoodFreshness.FRESH);
            case FOUR_LEAF_CLOVER -> new Food("Trèfle à quatres feuilles", 20, foodType, FoodFreshness.FRESH);
            case ROCK_OIL -> new Food("Huile de roche", 5, foodType, FoodFreshness.FRESH);
            case BEET_JUICE -> new Food("Jus de betterave", 30, foodType, FoodFreshness.FRESH);
            case HONEY -> new Food("Miel", 35, foodType, FoodFreshness.FRESH);
            case WINE -> new Food("Vin", 70, foodType, FoodFreshness.FRESH);
            case MEAD -> new Food("Hydromel", 50, foodType, FoodFreshness.FRESH);
            case UNICOR_MILK -> new Food("Lait de licorne à deux têtes", 100, foodType, FoodFreshness.FRESH);
            case IDEFIX_HAIR -> new Food("Poils d'Idéfix", 5, foodType, FoodFreshness.FRESH);
            case SECRET_INGREDIENT -> new Food("Ingrédient secret", 150, foodType, FoodFreshness.FRESH);
            default -> throw new IllegalArgumentException("/!\\ Unknown food type: " + foodType);
        };
    }
}
