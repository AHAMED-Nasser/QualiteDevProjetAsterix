package src.Interfaces;

import src.Characters.Character;
import src.Food.Food;

public interface ICharacterAction {
//    void fight(Character opponent);
    void takeDamage(int damage);
    void heal(int amount);
    void eat(Food food);
    void drinkMagicPotion(int potionAmount);
    boolean isDead();
    void displayInformation();
}
