package src.Interfaces;

import src.AbstractClass.AbstractPlayer;

public interface ICharacterAction {
    void fight(AbstractPlayer opponent);
    void takeDamage(int damage);
    void heal(int amount);
    void eat();
    void drinkMagicPotion();
    void checkDeath();
}
