package src.characters;

import src.food.Food;

public interface ICharacterAction {
//    void fight(Character opponent);
    void takeDamage(int damage);
    void heal(int amount);
    void eat(Food food);
    void drinkMagicPotion(int potionAmount);
    boolean isDead();
    void displayFullInformation();
    void displayMinInformation();
}
