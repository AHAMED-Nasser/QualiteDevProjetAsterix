package src.main.java.place;

import src.main.java.Enum.place.TypePlace;
import src.main.java.characters.Character;
import src.main.java.food.Food;

import java.util.List;

public class BattleField extends Place implements IBattleField {

    public BattleField(String name, int surface, TypePlace typePlace, List<Character> characterList, List<Food> foodList) {
        super(name, surface, typePlace, characterList, foodList);
    }

}
