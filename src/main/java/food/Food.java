package src.main.java.food;

import src.main.java.Enum.food.FoodCategory;
import src.main.java.Enum.food.FoodFreshness;
import src.main.java.Enum.food.FoodType;

public class Food {

    private String name;
    private int foodNutrition;
    private FoodType foodType;
    private FoodFreshness foodFreshness;
    private FoodCategory foodCategory;

    /**
     * Food constructor
     * @param name
     * @param foodNutrition
     * @param foodType
     * @param foodFreshness
     * @param foodCategory
     */
    public Food(String name, int foodNutrition, FoodType foodType, FoodFreshness foodFreshness, FoodCategory foodCategory) {
        this.name = name;
        this.foodNutrition = foodNutrition;
        this.foodType = foodType;
        this.foodFreshness = foodFreshness;
        this.foodCategory = foodCategory;
    }

    // Getter
    public String getName() { return this.name;}
    public int getFoodNutrition() { return this.foodNutrition; }
    public FoodType getFoodType() { return this.foodType; }
    public FoodFreshness getFoodFreshness() { return this.foodFreshness; }
    public FoodCategory getFoodCategory() { return this.foodCategory; }

    // Setter
    public void setFoodFreshness(FoodFreshness foodFreshness) { this.foodFreshness = foodFreshness; }
}
