package src.Food;

import src.Statistics;

public abstract class AbstractFood {

    private String name;
    private Statistics foodNutrition;
    private FoodType foodType;
    private FoodFreshness foodFreshness;

    public AbstractFood(String name, Statistics foodNutrition, FoodType foodType, FoodFreshness foodFreshness) {
        this.name = name;
        this.foodNutrition = foodNutrition;
        this.foodType = foodType;
        this.foodFreshness = foodFreshness;
    }

    // Getter
    public String getName() { return this.name;}
    public Statistics getFoodNutrition() { return this.foodNutrition; }
    public FoodType getFoodType() { return this.foodType; }
    public FoodFreshness getFoodFreshness() { return this.foodFreshness; }

}
