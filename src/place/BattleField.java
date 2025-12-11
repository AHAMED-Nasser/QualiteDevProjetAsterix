package src.place;

import src.Enum.place.TypePlace;
import src.characters.Character;
import src.food.Food;

import java.util.List;

public class BattleField extends Place implements IBattleField {

    public BattleField(String name, int surface, TypePlace typePlace, List<Character> characterList, List<Food> foodList) {
        super(name, surface, typePlace, characterList, foodList);
    }

    @Override
    public void addCharacter(List<Character> characters) {
        this.setCharacterList(characters);
    }
}
