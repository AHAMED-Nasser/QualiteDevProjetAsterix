package src.Interfaces.Place;

import src.Food.Food;
import src.Characters.Character;

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
}
