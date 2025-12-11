package src.Place;

import src.Characters.Character;
import src.Food.Food;
import src.Enum.Place.TypePlace;
import src.Interfaces.Place.ISafePlace;
import src.Statistics;

import java.util.List;
import java.util.Map;

public class SafePlace extends Place implements ISafePlace {

    private Character clanChief;

    public SafePlace(String name, int surface, TypePlace typePlace, List<Character> characterList, List<Food> foodList, Character clanChief) {
        super(name, surface, typePlace, characterList, foodList);
        this.clanChief = clanChief;
    }

    @Override
    public void displayPlaceInfo() {
        System.out.println("<----------- INFORMATION DU LIEU: " + this.getName() + " ------------>");
        System.out.println("Chef du clan: " + this.clanChief.getName());
        System.out.println("Nom: " + this.getName());
        System.out.println("Surface: " + this.getSurface() + "m²");
        System.out.println("Type de village: " + this.getTypePlace());
    }

    @Override
    public void displayCharacter() {
        System.out.println("<---- Personnage présent dans le lieu: " + this.getName() + " ---->");
        for (Character character : this.getCharacterList()) {
            System.out.println("-> " + character.getName());
        }
    }

    @Override
    public void displayCharacterMinInfo() {
        System.out.println();
        System.out.println("<---- Information des personnages ---->");
        for (Character character : this.getCharacterList()) {
            character.displayMinInformation();
        }
    }

    @Override
    public void displayCharacterFullInfo() {
        System.out.println();
        System.out.println("<---- Information des personnages ---->");
        for (Character character : this.getCharacterList()) {
            character.displayFullInformation();
        }
    }

    @Override
    public void displayFood() {
        System.out.println("<---- Aliment présent dans le lieu: " + this.getName() + " ---->");
        System.out.println("Nombre aliment dans le lieu: " + this.getFoodList().size());
        Map<String, Long> countByType = getFoodList().stream()
                .map(food -> food.getName()) // Assurez-vous que Food a une méthode getName()
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
    public void healAllCharacters() {
        for (Character character : this.getCharacterList()) {
            character.heal(100);
        }
    }

    @Override
    public void healOneCharacters(Character character) {
        character.heal(100);
    }

    @Override
    public void addFood(Food food) {
        this.getFoodList().add(food);
    }

    @Override
    public void feedAllCharacters() {
        System.out.println("Le chef de clan à nourrit tout le monde.");
        for (Character character : this.getCharacterList()) {
            character.setHunger(new Statistics(100, 0, 100));
        }
    }

    @Override
    public void feedOneCharacters(Character character) {
        System.out.println("Le chef de clan à nourrit " + character.getName());
        character.setHunger(new Statistics(100, 0, 100));
    }


}
