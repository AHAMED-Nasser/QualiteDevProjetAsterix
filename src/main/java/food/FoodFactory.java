package src.main.java.food;

import src.main.java.Enum.food.FoodCategory;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;

public class FoodFactory {

    public Food createFood(FoodType foodType) {
        return switch (foodType) {
            case WILD_BOAR -> new Food("Sanglier", 60, foodType, FoodFreshness.FRESH, FoodCategory.MEAT);
            case FAIRLY_FRESH_FISH -> new Food("Poisson passablement frais", 40, foodType, FoodFreshness.FAIRLY_FRESH, FoodCategory.FISH);
            case NOT_FRESH_FISH -> new Food("Poisson pas frais", -30, foodType, FoodFreshness.NOT_FRESH, FoodCategory.FISH);
            case MISTLETOE -> new Food("Gui", 10, foodType, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
            case LOBSTER -> new Food("Homard", 50, foodType, FoodFreshness.FRESH, FoodCategory.FISH);
            case STRAWBERRY -> new Food("Fraise", 15, foodType, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
            case CARROT -> new Food("Carrot", 25, foodType, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
            case SALT -> new Food("Sel", 5, foodType, FoodFreshness.FRESH, FoodCategory.MINERAL);
            case FOUR_LEAF_CLOVER -> new Food("Trèfle à quatres feuilles", 20, foodType, FoodFreshness.FRESH, FoodCategory.VEGETABLE);
            case ROCK_OIL -> new Food("Huile de roche", 5, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case BEET_JUICE -> new Food("Jus de betterave", 30, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case HONEY -> new Food("Miel", 35, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case WINE -> new Food("Vin", 70, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case MEAD -> new Food("Hydromel", 50, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case UNICOR_MILK -> new Food("Lait de licorne à deux têtes", 100, foodType, FoodFreshness.FRESH, FoodCategory.DRINK);
            case IDEFIX_HAIR -> new Food("Poils d'Idéfix", 5, foodType, FoodFreshness.FRESH, FoodCategory.UNKNOWN);
            case SECRET_INGREDIENT -> new Food("Ingrédient secret", 150, foodType, FoodFreshness.FRESH, FoodCategory.UNKNOWN);
            default -> throw new IllegalArgumentException("/!\\ Unknown food type: " + foodType);
        };
    }
}
