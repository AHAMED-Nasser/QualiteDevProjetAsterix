package src.place;

import src.food.Food;
import src.characters.Character;

import java.util.List;

public interface ISafePlace {
    void displayCharacter();
    void displayCharacterMinInfo();
    void displayCharacterFullInfo();
    void displayFood();
    void displayPlaceInfo();
    void addCharacter(Character character);
    void removeCharacter(Character character);
    void healAllCharacters();
    void healOneCharacters(Character character);
    void addFood(Food food);
    void feedAllCharacters();
    void feedOneCharacters(Character character);
    List<Character> transferCharacter(int nbCharacter);
}
