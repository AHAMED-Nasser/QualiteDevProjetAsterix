package src.place;

import src.characters.Character;

public interface IPlace {
    void displayCharacter();
    void displayCharacterMinInfo();
    void displayFood();
    void addCharacter(Character character);
    void removeCharacter(Character character);
}
