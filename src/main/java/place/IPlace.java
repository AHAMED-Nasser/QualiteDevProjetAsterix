package src.main.java.place;

import src.main.java.characters.Character;

public interface IPlace {
    void displayCharacter();
    void displayCharacterMinInfo();
    void displayFood();
    void addCharacter(Character character);
    void removeCharacter(Character character);
}
