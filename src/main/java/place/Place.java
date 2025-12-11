package src.main.java.place;

import src.main.java.food.Food;
import src.main.java.Enum.place.TypePlace;
import src.main.java.characters.Character;
import src.main.java.food.StockFood;

import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class Place implements IPlace {

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

    @Override
    public void displayCharacter() {
        System.out.println("<---- Personnage présent dans le lieu: " + this.getName() + " ---->");
        System.out.println("Nombre de personnage: " + this.getCharacterList().size());
        for (Character character : this.getCharacterList()) {
            System.out.println("-> " + character.getName());
        }
    }

    @Override
    public void displayCharacterMinInfo() {
        System.out.println();
        System.out.println("<---- Information des personnages ---->");
        System.out.println("Nombre de personnage: " + this.getCharacterList().size());
        for (Character character : this.getCharacterList()) {
            character.displayMinInformation();
        }
    }

    @Override
    public void displayFood() {
        System.out.println("<---- Aliment présent dans le lieu: " + this.getName() + " ---->");
        System.out.println("Nombre aliment dans le lieu: " + this.getFoodList().size());
        Map<String, Long> countByType = getFoodList().stream()
                .map(Food::getName) // Assurez-vous que Food a une méthode getName()
                .collect(java.util.stream.Collectors.groupingBy(name -> name, java.util.stream.Collectors.counting()));

        countByType.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println(" - " + entry.getKey() + " : " + entry.getValue() + " unités"));
    }

    @Override
    public void addCharacter(Character character) {
        this.getCharacterList().add(character);
    }

    @Override
    public void removeCharacter(Character character) {
        this.getCharacterList().remove(character);
    }

    @Override
    public void addFood(Food food) {
        this.foodList.add(food);
    }
}
