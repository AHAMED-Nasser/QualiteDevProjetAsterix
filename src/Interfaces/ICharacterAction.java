package src.Interfaces;

import src.Characters.Character;

public interface ICharacterAction {
//    void fight(Character opponent);
    void takeDamage(int damage);
    void heal(int amount);
    void eat(int hungerAmount);
    void drinkMagicPotion(int potionAmount);
    boolean isDead();
}
