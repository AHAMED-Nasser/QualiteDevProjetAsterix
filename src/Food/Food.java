package src.Food;

import src.Statistics;

public class Food {

    private String name;
    private int foodNutrition;
    private FoodType foodType;
    private FoodFreshness foodFreshness;

    public Food(String name, int foodNutrition, FoodType foodType, FoodFreshness foodFreshness) {
        this.name = name;
        this.foodNutrition = foodNutrition;
        this.foodType = foodType;
        this.foodFreshness = foodFreshness;
    }

    // Getter
    public String getName() { return this.name;}
    public int getFoodNutrition() { return this.foodNutrition; }
    public FoodType getFoodType() { return this.foodType; }
    public FoodFreshness getFoodFreshness() { return this.foodFreshness; }

}
