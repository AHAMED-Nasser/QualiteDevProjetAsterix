package src;

import src.Characters.Character;
import src.Characters.CharacterFactory;
import src.Characters.CreatePopulation;
import src.Enum.Character.Faction;
import src.Enum.Character.Occupation;
import src.Food.Food;
import src.Food.FoodFactory;
import src.Enum.Food.FoodType;
import src.Enum.Place.TypePlace;
import src.Food.StockFood;
import src.Place.PlaceFactory;
import src.Place.SafePlace;

import java.util.List;
import java.util.Map;

public class Game {

    public static void main(String[] var0) {

        // Create random population
        CreatePopulation populator = new CreatePopulation();
        List<Character> allCharacters = populator.generateSimulationPopulation(10, 10, 9);

        // Create clan chief
        CharacterFactory factory = new CharacterFactory();
        Character chef_clan = factory.createCharacter(Faction.GAULS, Occupation.CLAN_CHIEF, "Nasser");

        Character unRomain = factory.createCharacter(Faction.ROMAN, Occupation.LEGIONNAIRE, "Un Romain");
        Character unGaulois = factory.createCharacter(Faction.GAULS, Occupation.FORGERON, "Un Gaulois");

        // PLACE

        PlaceFactory placeFactory = new PlaceFactory();

        SafePlace pydertale = placeFactory.createSafePlace("Pydertale", TypePlace.GAULS_VILLAGE, chef_clan);

        System.out.println("--- Début de la simulation ---");

        for (int i = 0; i < allCharacters.size(); i++) {
            System.out.println(allCharacters.get(i).getName());
        }


        pydertale.displayFood();

//
//        pydertale.displayCharacter();
//        pydertale.displayCharacterMinInfo();
//
//        pydertale.displayPlaceInfo();
    }
}
