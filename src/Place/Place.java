package src.Place;

import src.Food.Food;
import src.Enum.Place.TypePlace;
import src.Characters.Character;
import src.Food.StockFood;

import java.util.List;
import java.util.Random;

public abstract class Place {

    private String name;
    private final int surface;
    private final TypePlace typePlace;
    private List<Character> characterList;
    private List<Food> foodList;

    StockFood stockFood = new StockFood();
    List<Food> foods = stockFood.generateInitialStock(new Random().nextInt(40, 99));

    public Place(String name, int surface, TypePlace typePlace, List<Character> characterList, List<Food> foodList) {
        this.name = name;
        this.surface = surface;
        this.typePlace = typePlace;
        this.characterList = characterList;
        this.foodList = foodList;

        // initialisation des aliment dans le lieu
        for (Food food : foods) {
            this.foodList.add(food);
        }

    }

    // Getter
    public String getName() { return this.name; }
    public int getSurface() { return this.surface; }
    public int getNumberOfCharacters() { return this.characterList.size(); }
    public List<Character> getCharacterList() { return this.characterList; }
    public List<Food> getFoodList() { return this.foodList; }
    public TypePlace getTypePlace() { return this.typePlace; }

    // Setter
    public void setName(String name) { this.name = name; }
    public void setCharacterList(List<Character> characterList) { this.characterList = characterList; }
    public void setFoodList(List<Food> foodList) { this.foodList = foodList; }
}
